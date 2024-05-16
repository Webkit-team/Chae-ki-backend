package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public Page<Challenge> readAllChallenges(Pageable pageable, String status, String category) {
        ChallengeStatus needStatus = ChallengeStatus.valueOf(status);

        if (category != null) { // 책분야와 status에 맞는 목록 조회
            return challengeRepository.findByStatusAndCategory(needStatus, category, pageable);
        }
        return challengeRepository.findByStatus(needStatus, pageable); // status로 챌린지 목록 조회
    }
}
