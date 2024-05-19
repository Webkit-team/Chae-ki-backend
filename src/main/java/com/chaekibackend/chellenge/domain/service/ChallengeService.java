package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeRepository;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import com.chaekibackend.chellenge.domain.interfaces.ChaekiTodayRepository;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeMemberRepository;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeRepository;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ChallengeMemberRepository challengeMemberRepository;
    private final UsersRepository usersRepository;
    private final BookRepository bookRepository;
    private final ChaekiTodayRepository chaekiTodayRepository;

    public List<Challenge> readAllChallenges(){
        return challengeRepository.findAll();
    }

    public Challenge readChallenge(Long id){
        return challengeRepository.findById(id).orElseThrow();
    }

    public List<Challenge> readMyChallenges(Long no){
       Users user = usersRepository.findByNo(no);
       List<Challenge> myChallenges = new ArrayList<>();

        List<ChallengeMember> challengeMemberList = challengeMemberRepository.findByUsers(user);
        for(ChallengeMember challengeMember : challengeMemberList){
            myChallenges.add(challengeMember.getChallenge());
        }

       return myChallenges;
    }

    @Transactional
    public Challenge createChallenge(ChallengeRequest.Create request) {
        Book choiceBook = bookRepository.findByNo(request.getBookNo());
        Challenge challenge = Challenge.from(request, choiceBook);

        return challengeRepository.save(challenge);
    }

    public List<ChaekiToday> readMyChaekiTodays(Long no){
        Optional<Users> users = usersRepository.findById(no);
        List<ChaekiToday> chaekiTodays = new ArrayList<>();

        users.ifPresent(u -> {
            List<ChallengeMember> myChallengeMember = challengeMemberRepository.findByUsers(u);
            for(ChallengeMember challengeMember : myChallengeMember){
                chaekiTodays.addAll(chaekiTodayRepository.findByChallengeMember(challengeMember));
            }
        });

        return chaekiTodays;
    }

    @Transactional
    public Challenge readByNo(Long no) {
        Optional<Challenge> optional = challengeRepository.findById(no);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "챌린지를 찾을 수 없습니다.");
        }
        return optional.get();
    }

    @Transactional
    public Page<Challenge> readAllChallenges(Pageable pageable, String status, String category) {
        ChallengeStatus needStatus = ChallengeStatus.valueOf(status);

        if (category != null) { // 책분야와 status에 맞는 목록 조회
            return challengeRepository.findByStatusAndCategory(needStatus, category, pageable);
        }
        return challengeRepository.findByStatus(needStatus, pageable); // status로 챌린지 목록 조회
    }

    @Transactional
    public Challenge save(Challenge challenge) {
        return challengeRepository.save(challenge);
    }
}
