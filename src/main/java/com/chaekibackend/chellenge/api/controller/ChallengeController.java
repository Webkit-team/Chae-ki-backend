package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.api.response.ReadingTimeResponse;
import com.chaekibackend.chellenge.application.ChallengeAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeAppService challengeAppService;

    // 챌린지 등록
    @PostMapping(value="/challenge")
    @ResponseBody
    public List<ChallengeResponse.Detail> CreateChallenge(@RequestBody ChallengeRequest.Create request){
        return challengeAppService.createChallenge(request);
    }

    // 챌린지 상세 조회
    @GetMapping("/challenge/{no}")
    @ResponseBody
    public ChallengeResponse.Detail GetChallenge(@PathVariable("no") Long no){
        return challengeAppService.readChallenge(no);
    }

    // 마이페이지 챌린지 목록 조회
    @GetMapping("/users/{uno}/challenges")
    @ResponseBody
    public List<ChallengeResponse.Detail> GetMyChallenges(@PathVariable("uno") Long uno){
        return challengeAppService.readMyChallenges(uno);
    }

    // 마이페이지 채키투데이 목록 조회
    @GetMapping("users/{uno}/chaekiTodays")
    @ResponseBody
    public List<ChaekiTodayResponse.Detail> GetMyChaekiTodays(@PathVariable("uno") Long uno){
        return challengeAppService.readMyChaekiTodays(uno);
    }

    // 마이페이지 독서 시간 목록 조회 (그래프용)
    @GetMapping("users/{uno}/myReadingTime")
    @ResponseBody
    public List<ReadingTimeResponse> GetMyReadingTimes(@PathVariable("uno") Long uno){
        return challengeAppService.readMyReadingTimes(uno);
    }
}
