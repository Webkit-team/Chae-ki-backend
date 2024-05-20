package com.chaekibackend.chellenge.application;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.api.response.ReadingTimeResponse;
import com.chaekibackend.chellenge.domain.entity.*;
import com.chaekibackend.chellenge.domain.service.ChaekiWeekService;
import com.chaekibackend.chellenge.domain.service.ChallengeMemberService;
import com.chaekibackend.chellenge.domain.service.ChallengeService;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeAppService {
    private final ChallengeService challengeService;
    private final BookRepository bookRepository;
    private final UsersService usersService;
    private final ChallengeMemberService memberService;
    private final ChaekiWeekService weekService;

    public ChallengeResponse.Detail createChallenge(ChallengeRequest.Create request) {
        Optional<Book> result = bookRepository.findById(request.getBookNo());
        if (result.isEmpty()) {
            log.error("존재하지 않는 book no입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 책을 조회하려고 했습니다.");
        }
        Book book = result.get();
        // 새 챌린지 생성
        Challenge challenge = Challenge.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .memberCount(0)
                .category(book.getCategory())
                .status(ChallengeStatus.RECRUITING)
                .book(book)
                .build();

        // 새 챌린지의 위크 4개 생성
        LocalDate start = challenge.getStartDate();
        List<ChaekiWeek> weeks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ChaekiWeek newWeek = ChaekiWeek
                    .builder()
                    .startDate(start)
                    .endDate(start.plusDays(6))
                    .challenge(challenge)
                    .build();
            weeks.add(newWeek);
            start = start.plusDays(7);
        }

        // 새 챌린지와 새 위크 저장
        challengeService.save(challenge);
        weeks.forEach(weekService::save);

        return ChallengeResponse.Detail.from(challenge, challenge.getBook());
    }

    public ChallengeResponse.Detail readChallenge(Long id) {
        Challenge challenge = challengeService.readChallenge(id);

        Book book = challenge.getBook();
        ChallengeResponse.Detail res = ChallengeResponse.Detail.from(challenge, book);

        return res;
    }

    public List<ChallengeResponse.Detail> readMyChallenges(Long uno) {
        List<Challenge> myChallenges = challengeService.readMyChallenges(uno);
        List<ChallengeResponse.Detail> myChallengesDetail = new ArrayList<>();

        for (Challenge challenge : myChallenges) {
            Book book = challenge.getBook();
            myChallengesDetail.add(ChallengeResponse.Detail.from(challenge, book));
        }

        return myChallengesDetail;
    }

    public List<ChaekiTodayResponse.Detail> readMyChaekiTodays(Long uno) {
        List<ChaekiToday> myChaekiTodays = challengeService.readMyChaekiTodays(uno);
        List<ChaekiTodayResponse.Detail> myChaekiTodaysDetail = new ArrayList<>();

        for (ChaekiToday chaekiToday : myChaekiTodays) {
            myChaekiTodaysDetail.add(ChaekiTodayResponse.Detail.from(chaekiToday));
        }

        return myChaekiTodaysDetail;
    }

    public List<ReadingTimeResponse> readMyReadingTimes(Long uno) {
        List<ChaekiToday> myChaekiTodays = challengeService.readMyChaekiTodays(uno);
        System.out.println("myCheckiTodays = " + myChaekiTodays);
        List<ReadingTimeResponse.Detail> ofFirstChallenge = new ArrayList<>();
        List<ReadingTimeResponse> myReadingTimes = new ArrayList<>();

        Long challengeNo = null;
        if(!(myChaekiTodays.isEmpty())){
            challengeNo = myChaekiTodays.get(0).getChallengeMember().getChallenge().getNo();
        }

        // List를 멤버 변수로 가진 ResponseDto를 가진 List를 반환함
        for (ChaekiToday chaekiToday : myChaekiTodays) {
            if (!(challengeNo.equals(chaekiToday.getChallengeMember().getChallenge().getNo()))) {
                myReadingTimes.add(ReadingTimeResponse.createReadingTimeResponse(chaekiToday, ofFirstChallenge));
                challengeNo = chaekiToday.getChallengeMember().getChallenge().getNo();
                ofFirstChallenge.clear();
            }
            ofFirstChallenge.add(ReadingTimeResponse.Detail.from(chaekiToday));
        }

        myReadingTimes.add(ReadingTimeResponse.createReadingTimeResponse(
                                myChaekiTodays.get(myChaekiTodays.size()-1), ofFirstChallenge));

        return myReadingTimes;
    }

    @Transactional
    public ChallengeResponse.Join joinChallenge(Long cno, Long uno) {
        Challenge challenge = challengeService.readByNo(cno);
        Users user = usersService.readByNo(uno);
        // 새 챌린지 멤버 생성
        ChallengeMember member = ChallengeMember.builder()
                .users(user)
                .challenge(challenge)
                .build();
        // 새 챌린지 멤버 등록
        ChallengeMember savedMember = memberService.save(member);

        return ChallengeResponse.Join.from(savedMember);
    }

    public Page<ChallengeResponse.Retrieval> readAllChallenges(Pageable pageable, String status, String category, String searchQuery) {
        // 1. 챌린지 검색 기능
        if (searchQuery != null) {
            List<Book> books = bookRepository.findByNameOrWriter(searchQuery);
            List<Challenge> challenges = new ArrayList<>();
            ChallengeStatus challengeStatus = ChallengeStatus.valueOf(status);
//            log.info("status : " + challengeStatus.name()); // todo: 추후 삭제
            for (Book book : books) {
                for (Challenge challenge : book.getChallengeList()) {
                    if (challengeStatus == challenge.getStatus()) {
                        challenges.add(challenge);
                    }
                }
            }
//            log.info("challenges : " + challenges.size()); // todo: 추후 삭제
            List<ChallengeResponse.Retrieval> response = challenges.stream()
                    .map(ChallengeResponse.Retrieval::from)
                    .toList();

            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), response.size());
            List<ChallengeResponse.Retrieval> subList = response.subList(start, end);

            return new PageImpl<>(subList, pageable, response.size());
        }


        // 2. 챌린지 목록 조회 기능
        return challengeService
                .readAllChallenges(pageable, status, category)
                .map(ChallengeResponse.Retrieval::from);
    }
}
