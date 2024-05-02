package com.chaekibackend.chellenge.application;

import com.chaekibackend.chellenge.domain.service.ChallengeService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeAppService {
    private final ChallengeService challengeService;
}
