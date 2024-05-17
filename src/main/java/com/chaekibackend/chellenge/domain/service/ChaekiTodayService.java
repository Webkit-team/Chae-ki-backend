package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.interfaces.ChaekiTodayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChaekiTodayService {
    private final ChaekiTodayRepository todayRepository;

    @Transactional
    public ChaekiToday save(ChaekiToday today) {
        return todayRepository.save(today);
    }
}
