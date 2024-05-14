package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.ChallengeRequest;
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

    @GetMapping("/challenge/{id}")
    @ResponseBody
    public ChallengeResponse.Detail GetChallenge(@PathVariable("id") Long id){
        return challengeAppService.readChallenge(id);
    }
}
