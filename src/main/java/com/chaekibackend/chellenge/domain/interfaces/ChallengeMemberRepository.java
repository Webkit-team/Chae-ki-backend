package com.chaekibackend.chellenge.domain.interfaces;

import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.users.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {
    List<ChallengeMember> findByUsers(Users users);

    @Query("SELECT cm FROM ChallengeMember cm " +
            "JOIN cm.users u " +
            "JOIN cm.challenge c " +
            "WHERE u.no = :userNo AND c.no = :challengeNo")
    ChallengeMember findByUserAndChallenge(@Param("userNo") Long userNo, @Param("challengeNo") Long challengeNo);
}
