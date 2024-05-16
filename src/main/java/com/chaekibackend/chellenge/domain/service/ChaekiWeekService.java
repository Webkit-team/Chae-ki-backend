package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeek;
import com.chaekibackend.chellenge.domain.interfaces.ChaekiWeekRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChaekiWeekService {
    private final ChaekiWeekRepository weekRepository;

    @Transactional
    public ChaekiWeek save(ChaekiWeek week) {
        return weekRepository.save(week);
    }
}
