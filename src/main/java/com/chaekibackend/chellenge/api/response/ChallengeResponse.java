package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ChallengeResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "ChallengeResponse.TodaySave")
    public static class TodaySave {
        private Long todayNo;
        private Long challengeNo;
        private Long userNo;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "ChallengeResponse.Join")
    public static class Join {
        private Long challengeNo;
        private Long userNo;

        public static Join from(ChallengeMember member) {
            return Join
                    .builder()
                    .challengeNo(member.getChallenge().getNo())
                    .userNo(member.getUsers().getNo())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "ChallengeResponse.Retrieval")
    public static class Retrieval {
        private Long no;
        private String name;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer memberCount;
        private String category;
        private ChallengeStatus status;
        private BookResponse.Detail book;

        public static ChallengeResponse.Retrieval from(Challenge challenge) {
            return ChallengeResponse.Retrieval
                    .builder()
                    .no(challenge.getNo())
                    .name(challenge.getName())
                    .description(challenge.getDescription())
                    .startDate(challenge.getStartDate())
                    .endDate(challenge.getEndDate())
                    .memberCount(challenge.getMemberCount())
                    .category(challenge.getCategory())
                    .status(challenge.getStatus())
                    .book(BookResponse.Detail.from(challenge.getBook()))
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "ChallengeResponse.Update")
    public static class Update{
        private Long no;
        private String name;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer memberCount;
        private Book book;
        public static ChallengeResponse.Update from(Challenge challenge){
            return Update.builder()
                    .no(challenge.getNo())
                    .name(challenge.getName())
                    .description(challenge.getDescription())
                    .startDate(challenge.getStartDate())
                    .endDate(challenge.getEndDate())
                    .memberCount(challenge.getMemberCount())
                    .book(challenge.getBook())
                    .build();
        }
    }
}
