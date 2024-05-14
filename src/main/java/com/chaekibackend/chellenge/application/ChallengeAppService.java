package com.chaekibackend.chellenge.application;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.service.ChallengeService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAppService {
    private final ChallengeService challengeService;
    private final BookRepository bookRepository;

    public List<ChallengeResponse.Detail> createChallenge(ChallengeRequest.Create request) {
        List<Challenge> challengeList = challengeService.createChallenge(request);
        List<ChallengeResponse.Detail> challengeResponses = new ArrayList<>();

        for(Challenge challenge : challengeList){
            Book book = challenge.getBook();
            challengeResponses.add(ChallengeResponse.Detail.from(challenge, book));
        }

        return challengeResponses;
    }

    public ChallengeResponse.Detail readChallenge(Long id){
        Challenge challenge = challengeService.readChallenge(id);

        Book book = challenge.getBook();
        ChallengeResponse.Detail res = ChallengeResponse.Detail.from(challenge, book);

        return res;
    }

}
