package com.chaekibackend.chellenge.domain.interfaces;

import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.users.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

    List<ChallengeMember> findByUsers(Users users);
}
