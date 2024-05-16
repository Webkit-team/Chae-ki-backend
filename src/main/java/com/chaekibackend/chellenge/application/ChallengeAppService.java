package com.chaekibackend.chellenge.application;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.api.response.ReadingTimeResponse;
import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.service.ChallengeService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeAppService {
    private final ChallengeService challengeService;

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

    public List<ChallengeResponse.Detail> readMyChallenges(Long uno){
        List<Challenge> myChallenges = challengeService.readMyChallenges(uno);
        List<ChallengeResponse.Detail> myChallengesDetail = new ArrayList<>();

        for(Challenge challenge : myChallenges){
            Book book = challenge.getBook();
            myChallengesDetail.add(ChallengeResponse.Detail.from(challenge, book));
        }

        return myChallengesDetail;
    }

    public List<ChaekiTodayResponse.Detail> readMyChaekiTodays(Long uno){
        List<ChaekiToday> myChaekiTodays = challengeService.readMyChaekiTodays(uno);
        List<ChaekiTodayResponse.Detail> myChaekiTodaysDetail = new ArrayList<>();

        for(ChaekiToday chaekiToday : myChaekiTodays){
            myChaekiTodaysDetail.add(ChaekiTodayResponse.Detail.from(chaekiToday));
        }

        return myChaekiTodaysDetail;
    }

    public List<ReadingTimeResponse> readMyReadingTimes(Long uno){
        List<ChaekiToday> myChaekiTodays = challengeService.readMyChaekiTodays(uno);
        List<ReadingTimeResponse.Detail> ofFirstChallenge = new ArrayList<>();
        List<ReadingTimeResponse> myReadingTimes = new ArrayList<>();
        
        // 여기서 에러남 -> DB에 데이터가 없어서 그런 듯!
        Long challengeNo = myChaekiTodays.get(0).getChallengeMember().getChallenge().getNo();

        // List를 멤버 변수로 가진 ResponseDto로 List를 만들어보기
        for(ChaekiToday chaekiToday : myChaekiTodays){
            if(!(challengeNo.equals(chaekiToday.getChallengeMember().getChallenge().getNo()))){
                myReadingTimes.add(ReadingTimeResponse.createReadingTimeResponse(chaekiToday, ofFirstChallenge));
                challengeNo = chaekiToday.getChallengeMember().getChallenge().getNo();
                ofFirstChallenge.clear();
            }
            ofFirstChallenge.add(ReadingTimeResponse.Detail.from(chaekiToday));
        }
        return myReadingTimes;
    }


}
