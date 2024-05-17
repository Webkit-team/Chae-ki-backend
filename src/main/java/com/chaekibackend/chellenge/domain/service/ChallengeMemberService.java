package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeMemberRepository;
import com.chaekibackend.users.domain.entity.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChallengeMemberService {
    private final ChallengeMemberRepository memberRepository;

    @Transactional
    public ChallengeMember save(ChallengeMember member) {
        return memberRepository.save(member);
    }

    @Transactional
    public ChallengeMember readByUserAndChallenge(Long userNo, Long challengeNo) {
        return memberRepository.findByUserAndChallenge(userNo, challengeNo);
    }
}
