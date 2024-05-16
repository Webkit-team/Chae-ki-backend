package com.chaekibackend.chellenge.domain.interfaces;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaekiWeekRepository extends JpaRepository<ChaekiWeek, Long> {
}
