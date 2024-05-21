package com.chaekibackend.chellenge.application;

import com.chaekibackend.chellenge.api.request.CommentRequest;
import com.chaekibackend.chellenge.api.response.CommentResponse;
import com.chaekibackend.chellenge.domain.entity.ChaekiWeek;
import com.chaekibackend.chellenge.domain.entity.ChaekiWeekComment;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.service.ChaekiWeekCommentService;
import com.chaekibackend.chellenge.domain.service.ChaekiWeekService;
import com.chaekibackend.chellenge.domain.service.ChallengeMemberService;
import com.chaekibackend.chellenge.domain.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentAppService {
    private final ChaekiWeekCommentService commentService;
    private final ChallengeService challengeService;
    private final ChaekiWeekService weekService;
    private final ChallengeMemberService memberService;

    public CommentResponse.Register registerComment(
            Long userNo,
            Long challengeNo,
            CommentRequest.Register request
    ) {
        // challengeNo로 챌린지 조회
        Challenge challenge = challengeService.readByNo(challengeNo);
        if (challenge == null) {
            log.error("존재하지 않는 챌린지입니다.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 챌린지입니다.");
        }

        // 챌린지, 오늘날짜로 위크 조회
        LocalDateTime now = LocalDateTime.now();
        Optional<ChaekiWeek> weekOpt = weekService.findByChallengeAndDate(challenge, LocalDate.from(now));
        if (weekOpt.isEmpty()) {
            log.error("존재하지 않는 위크입니다.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 위크입니다.");
        }

        // uno, challengeNo 로 멤버 조회
        Optional<ChallengeMember> memberOpt = memberService.readByUserAndChallenge(userNo, challengeNo);
        if (memberOpt.isEmpty()) {
            log.error("존재하지 않는 챌린지 멤버입니다.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 챌린지 멤버입니다.");
        }

        // 위크, 멤버와 연결된 코멘트 생성
        ChaekiWeekComment comment = ChaekiWeekComment.builder()
                .content(request.getContent())
                .likeCount(0)
                .visible(true)
                .createdAt(now)
                .challengeMember(memberOpt.get())
                .chaekiWeek(weekOpt.get())
                .build();

        // 코멘트 저장
        ChaekiWeekComment savedComment = commentService.save(comment);

        return CommentResponse.Register.from(savedComment);
    }
}
