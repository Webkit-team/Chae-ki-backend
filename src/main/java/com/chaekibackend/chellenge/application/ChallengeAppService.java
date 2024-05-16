package com.chaekibackend.chellenge.application;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import com.chaekibackend.chellenge.domain.service.ChallengeMemberService;
import com.chaekibackend.chellenge.domain.service.ChallengeService;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeAppService {
    private final ChallengeService challengeService;
    private final BookRepository bookRepository;
    private final UsersService usersService;
    private final ChallengeMemberService memberService;

    public ChallengeResponse.Join joinChallenge(ChallengeRequest.Join join) {
        Challenge challenge = challengeService.readByNo(join.getCno());
        Users user = usersService.readByNo(join.getUno());
        ChallengeMember newMember = ChallengeMember.createNewMember(challenge, user);
        ChallengeMember savedMember = memberService.save(newMember);

        return ChallengeResponse.Join.from(savedMember);
    }

    public Page<ChallengeResponse.Retrieval> readAllChallenges(Pageable pageable, String status, String category, String searchQuery) {
        // 1. 챌린지 검색 기능
        if (searchQuery != null) {
            List<Book> books = bookRepository.findByNameOrWriter(searchQuery);
            List<Challenge> challenges = new ArrayList<>();
            ChallengeStatus challengeStatus = ChallengeStatus.valueOf(status);
//            log.info("status : " + challengeStatus.name()); // todo: 추후 삭제
            for (Book book : books) {
                for (Challenge challenge : book.getChallengeList()) {
                    if(challengeStatus == challenge.getStatus()) {
                        challenges.add(challenge);
                    }
                }
            }
//            log.info("challenges : " + challenges.size()); // todo: 추후 삭제
            List<ChallengeResponse.Retrieval> response = challenges.stream()
                    .map(ChallengeResponse.Retrieval::from)
                    .toList();

            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), response.size());
            List<ChallengeResponse.Retrieval> subList = response.subList(start, end);

            return new PageImpl<>(subList, pageable, response.size());
        }

        // 2. 챌린지 목록 조회 기능
        return challengeService.readAllChallenges(pageable, status, category)
                .map(ChallengeResponse.Retrieval::from);
    }
}
