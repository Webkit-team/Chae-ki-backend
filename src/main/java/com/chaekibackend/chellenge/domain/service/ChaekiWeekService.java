package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeek;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.interfaces.ChaekiWeekRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ChaekiWeekService {
    private final ChaekiWeekRepository weekRepository;

    @Transactional
    public ChaekiWeek save(ChaekiWeek week) {
        return weekRepository.save(week);
    }

    @Transactional
    public ChaekiWeek findByChallengeAndUser(Long challengeNo, Long userNo) {
        return weekRepository.findByChallengeAndUser(challengeNo, userNo);
    }

    public ChaekiWeek findByChallengeAndDate(Challenge challenge, LocalDate date) {
        return challenge.getWeekList()
                .stream()
                .filter(week -> week.included(date))
                .findFirst()
                .get();
    }
}
