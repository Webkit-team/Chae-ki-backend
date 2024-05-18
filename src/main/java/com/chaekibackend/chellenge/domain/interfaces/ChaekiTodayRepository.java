package com.chaekibackend.chellenge.domain.interfaces;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChaekiTodayRepository extends JpaRepository<ChaekiToday, Long> {
    List<ChaekiToday> findByChallengeMember(ChallengeMember challengeMember);

    @Query("SELECT ct FROM ChaekiToday ct " +
            "JOIN ct.challengeMember cm " +
            "JOIN cm.users u " +
            "JOIN cm.challenge c " +
            "WHERE u.no = :userNo AND c.no = :challengeNo AND ct.createdAt = :date")
    Optional<ChaekiToday> findByChallengeAndUserAndDate(@Param("challengeNo") Long challengeNo,
                                                 @Param("userNo") Long userNo,
                                                 @Param("date") LocalDate date);

}
