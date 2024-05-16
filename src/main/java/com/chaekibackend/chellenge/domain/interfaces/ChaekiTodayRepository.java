package com.chaekibackend.chellenge.domain.interfaces;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChaekiTodayRepository extends JpaRepository<ChaekiToday, Long> {
    List<ChaekiToday> findByChallengeMember(ChallengeMember challengeMember);
}
