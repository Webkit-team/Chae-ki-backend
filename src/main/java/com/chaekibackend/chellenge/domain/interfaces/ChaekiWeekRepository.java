package com.chaekibackend.chellenge.domain.interfaces;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChaekiWeekRepository extends JpaRepository<ChaekiWeek, Long> {
    @Query("SELECT w FROM ChaekiWeek w " +
            "JOIN w.challenge c " +
            "JOIN w.challengeMember.users u " +
            "WHERE c.no = :challengeNo AND u.no = :userNo")
    ChaekiWeek findByChallengeAndUser(@Param("challengeNo") Long challengeNo, @Param("userNo") Long userNo);
}
