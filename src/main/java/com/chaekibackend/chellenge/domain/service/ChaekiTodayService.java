package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.interfaces.ChaekiTodayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChaekiTodayService {
    private final ChaekiTodayRepository todayRepository;

    @Transactional
    public ChaekiToday save(ChaekiToday today) {
        return todayRepository.save(today);
    }

    @Transactional
    public ChaekiToday findByChallengeAndUserAndDate(Long challengeNo, Long userNo, LocalDate now) {
        Optional<ChaekiToday> result = todayRepository.findByChallengeAndUserAndDate(challengeNo, userNo, now);
        if (result.isEmpty()) {
            log.error("존재하지 않는 채키투데이입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 채키투데이입니다.");
        }

        return result.get();
    }

    @Transactional
    public ChaekiToday findByNo(Long no) {
        Optional<ChaekiToday> optional = todayRepository.findById(no);
        if (optional.isEmpty()) {
            log.info("존재하지 않는 채키투데이입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 채키투데이입니다.");
        }

        return optional.get();
    }

    @Transactional
    public Page<ChaekiToday> fetchTodayList(Pageable pageable) {
        return todayRepository.findByOrderByLikeCountDesc(pageable);
    }
}
