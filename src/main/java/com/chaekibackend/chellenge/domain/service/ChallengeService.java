package com.chaekibackend.chellenge.domain.service;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final BookRepository bookRepository;

    public List<Challenge> readAllChallenges(){
        return challengeRepository.findAll();
    }

    public Challenge readChallenge(Long id){
        return challengeRepository.findById(id).orElseThrow();
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
}
