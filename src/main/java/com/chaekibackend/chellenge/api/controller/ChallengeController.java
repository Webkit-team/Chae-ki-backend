package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.application.ChallengeAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeAppService challengeAppService;

    @PostMapping(value="/challenge")
    @ResponseBody
    public List<ChallengeResponse.Detail> CreateChallenge(@RequestBody ChallengeRequest.Create request){
        return challengeAppService.createChallenge(request);
    }

    @GetMapping("/challenge/{uno}")
    @ResponseBody
    public ChallengeResponse.Detail GetChallenge(@PathVariable("uno") Long uno){
        return challengeAppService.readChallenge(uno);
    }

    @GetMapping("/users/{uno}/challenges")
    @ResponseBody
    public List<ChallengeResponse.Detail> GetMyChallenges(@PathVariable("uno") Long uno){
        return challengeAppService.readMyChallenges(uno);
    }

    @GetMapping("users/{uno}/chaekiTodays")
    @ResponseBody
    public List<ChaekiTodayResponse.Detail> GetMyChaekiTodays(@PathVariable("uno") Long uno){
        return challengeAppService.readMyChaekiTodays(uno);
    }
}
