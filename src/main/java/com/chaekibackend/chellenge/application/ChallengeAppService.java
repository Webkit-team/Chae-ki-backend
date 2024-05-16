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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        // todo: 쿼리 5번 실행되는 것 리팩토링 하기
        /*
            1. 챌린지 생성
            2. Week 4개 생성
         */
        // 1. 챌린지 생성
        Challenge savedChallenge = challengeService.createChallenge(request);
        // 2. Week 4개 생성
        LocalDate start = savedChallenge.getStartDate();
        for (int i = 0; i < 4; i++) {
            ChaekiWeek newWeek = ChaekiWeek.createNewWeek(savedChallenge, start);
            start = start.plusDays(7);
            weekService.save(newWeek);
        }

        return ChallengeResponse.Detail.from(savedChallenge, savedChallenge.getBook());
    }

    public ChallengeResponse.Detail readChallenge(Long id){
        Challenge challenge = challengeService.readChallenge(id);

        Book book = challenge.getBook();
        ChallengeResponse.Detail res = ChallengeResponse.Detail.from(challenge, book);

        return res;
    }

    public List<ChallengeResponse.Detail> readMyChallenges(Long uno){
        List<Challenge> myChallenges = challengeService.readMyChallenges(uno);
        List<ChallengeResponse.Detail> myChallengesDetail = new ArrayList<>();

        for(Challenge challenge : myChallenges){
            Book book = challenge.getBook();
            myChallengesDetail.add(ChallengeResponse.Detail.from(challenge, book));
        }

        return myChallengesDetail;
    }

    public List<ChaekiTodayResponse.Detail> readMyChaekiTodays(Long uno){
        List<ChaekiToday> myChaekiTodays = challengeService.readMyChaekiTodays(uno);
        List<ChaekiTodayResponse.Detail> myChaekiTodaysDetail = new ArrayList<>();

        for(ChaekiToday chaekiToday : myChaekiTodays){
            myChaekiTodaysDetail.add(ChaekiTodayResponse.Detail.from(chaekiToday));
        }

        return myChaekiTodaysDetail;
    }

    public List<ReadingTimeResponse> readMyReadingTimes(Long uno){
        List<ChaekiToday> myChaekiTodays = challengeService.readMyChaekiTodays(uno);
        List<ReadingTimeResponse.Detail> ofFirstChallenge = new ArrayList<>();
        List<ReadingTimeResponse> myReadingTimes = new ArrayList<>();
        
        // 여기서 에러남 -> DB에 데이터가 없어서 그런 듯!
        Long challengeNo = myChaekiTodays.get(0).getChallengeMember().getChallenge().getNo();

        // List를 멤버 변수로 가진 ResponseDto로 List를 만들어보기
        for(ChaekiToday chaekiToday : myChaekiTodays){
            if(!(challengeNo.equals(chaekiToday.getChallengeMember().getChallenge().getNo()))){
                myReadingTimes.add(ReadingTimeResponse.createReadingTimeResponse(chaekiToday, ofFirstChallenge));
                challengeNo = chaekiToday.getChallengeMember().getChallenge().getNo();
                ofFirstChallenge.clear();
            }
            ofFirstChallenge.add(ReadingTimeResponse.Detail.from(chaekiToday));
        }
        return myReadingTimes;
    }
    
//    public Boolean saveTimer(Long uno, ChallengeRequest.TimerSave timer) {
//
//    }

    public ChallengeResponse.Join joinChallenge(Long cno, Long uno) {
        Challenge challenge = challengeService.readByNo(cno);
        Users user = usersService.readByNo(uno);
        ChallengeMember newMember = ChallengeMember.createNewMember(challenge, user);
        ChallengeMember savedMember = memberService.save(newMember);

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
                    if(challengeStatus == challenge.getStatus()) {
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
        return challengeService.readAllChallenges(pageable, status, category)
                .map(ChallengeResponse.Retrieval::from);
    }
}
