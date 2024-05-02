package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.interfaces.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
}
