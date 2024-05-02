package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.application.ChallengeAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeAppService challengeAppService;
}
