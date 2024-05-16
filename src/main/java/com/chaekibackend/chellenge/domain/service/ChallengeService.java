package com.chaekibackend.chellenge.domain.service;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

    public List<Challenge> readMyChallenges(Long id){
        Optional<Users> users = usersRepository.findById(id);
        List<Challenge> myChallenges = new ArrayList<>();

        users.ifPresent(value -> {
            List<ChallengeMember> challengeMemberList = challengeMemberRepository.findByUsers(value);
            for(ChallengeMember challengeMember : challengeMemberList){
                myChallenges.add(challengeMember.getChallenge());
            }
        });

        // user를 넘겨야 함
//       List<ChallengeMember> challengeMemberList = challengeMemberRepository.findByUsers(id);
//       List<Challenge> myChallenges = new ArrayList<>();
//       for(ChallengeMember challengeMember : challengeMemberList){
//           myChallenges.add(challengeMember.getChallenge());
//       }

        System.out.println("myChallenges = " + myChallenges);
       return myChallenges;
    }

    @Transactional
    public List<Challenge> createChallenge(ChallengeRequest.Create request) {
        Book choiceBook = bookRepository.findByNo(request.getBookNo());

        System.out.println("choiceBook = " + choiceBook.getName());

        Challenge challenge = Challenge.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .memberCount(request.getMemberCount())
                .category(choiceBook.getCategory())
                .status(ChallengeStatus.RECRUITING)
                .book(choiceBook)
                .build();

        challengeRepository.save(challenge);

        return readAllChallenges();
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

//    public List<> readMyReadingTimes(Long no){
//
//    }
}
