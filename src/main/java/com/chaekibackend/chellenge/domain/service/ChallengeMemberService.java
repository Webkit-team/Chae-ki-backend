package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public Optional<ChallengeMember> readByUserAndChallenge(Long userNo, Long challengeNo) {
        return memberRepository.findByUserAndChallenge(userNo, challengeNo);
    }
}
