package com.chaekibackend.chellenge.application;

import com.chaekibackend.chellenge.api.request.ChaekiTodayRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.ChaekiWeek;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.service.ChaekiTodayService;
import com.chaekibackend.chellenge.domain.service.ChaekiWeekService;
import com.chaekibackend.chellenge.domain.service.ChallengeMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodayAppService {
    private final ChallengeMemberService memberService;
    private final ChaekiWeekService chaekiWeekService;
    private final ChaekiTodayService chaekiTodayService;

    public ChaekiTodayResponse.Posted checkUserPost(Long challengeNo, Long userNo) {
        LocalDate now = LocalDate.now();
        // todo: 예외발생 로직을 MemberService로 넣기
        Optional<ChallengeMember> optional = memberService.readByUserAndChallenge(userNo, challengeNo);
        if(optional.isEmpty()){
            log.error("존재하지 않는 챌린지 멤버입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 챌린지 멤버입니다.");
        }

        boolean posted = optional.get()
                .getTodayList()
                .stream()
                .anyMatch(today -> now.isEqual(today.getCreatedAt()));

        ChaekiTodayResponse.Posted result = ChaekiTodayResponse.Posted.builder()
                .posted(posted)
                .build();

        if(posted) {
            ChaekiToday today = chaekiTodayService.findByChallengeAndUserAndDate(challengeNo, userNo, LocalDate.now());
            result.setTodayNo(today.getNo());
        }

        return result;
    }

    public ChaekiTodayResponse.Creation registerToday(Long challengeNo, Long userNo, ChaekiTodayRequest.Creation request) {
        // 챌린지 멤버 조회
        Optional<ChallengeMember> optional = memberService.readByUserAndChallenge(userNo, challengeNo);
        if(optional.isEmpty()) {
            log.error("존재하지 않는 챌린지 멤버입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 챌린지 멤버입니다.");
        }
        ChallengeMember member = optional.get();

        // 특정 챌린지와 특정 날짜에 해당하는 채키위크 조회
        Optional<ChaekiWeek> optionalWeek = chaekiWeekService.findByChallengeAndDate(member.getChallenge(), LocalDate.now());
        if(optionalWeek.isEmpty()) {
            log.error("존재하지 않는 챌린지 위크입니다.");
            String now = LocalDate.now().toString();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, now + "가 포함되는 챌린지 위크가 없습니다.");
        }
        ChaekiWeek week = optionalWeek.get();

        // 채키투데이 저장
        ChaekiToday today = ChaekiToday.builder()
                .content(request.getContent())
                .readingPage(request.getReadingPage())
                .readingTime(request.getReadingTime())
                .createdAt(LocalDate.now())
                .likeCount(0)
                .visible(true)
                .chaekiWeek(week)
                .challengeMember(member)
                .build();
        ChaekiToday savedToday = chaekiTodayService.save(today);

        return ChaekiTodayResponse.Creation.from(savedToday);
    }
}
