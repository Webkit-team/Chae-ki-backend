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

    public Boolean checkUserPost(Long challengeNo, Long userNo) {
        LocalDate now = LocalDate.now();
        Optional<ChallengeMember> optional = memberService.readByUserAndChallenge(userNo, challengeNo);
        if(optional.isEmpty()){
            log.error("존재하지 않는 챌린지 멤버입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 챌린지 멤버입니다.");
        }

        return optional.get()
                .getTodayList()
                .stream()
                .anyMatch(today -> now.isEqual(today.getCreatedAt()));
    }

    public ChaekiTodayResponse.Creation registerToday(Long challengeNo, Long userNo, ChaekiTodayRequest.Creation request) {
        /*
            챌린지 멤버 조회
            당일 날짜가 포함된 채키 위크 조회
            채키 투데이 등록
         */
        Optional<ChallengeMember> optional = memberService.readByUserAndChallenge(userNo, challengeNo);
        if(optional.isEmpty()) {
            log.error("존재하지 않는 챌린지 멤버입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 챌린지 멤버입니다.");
        }
        ChallengeMember member = optional.get();
        ChaekiWeek week = chaekiWeekService.findByChallengeAndDate(member.getChallenge(), LocalDate.now());
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
