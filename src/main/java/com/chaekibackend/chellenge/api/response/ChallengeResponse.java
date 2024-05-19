package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.chellenge.domain.entity.ChallengeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class ChallengeResponse {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail{
        private Long no;
        private String name;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer memberCount;
        private BookResponse.Detail bookDetail;

        public static ChallengeResponse.Detail from(Challenge challenge, Book book){
            BookResponse.Detail bookDetail = BookResponse.Detail.from(book);

            return Detail.builder()
                    .no(challenge.getNo())
                    .name(challenge.getName())
                    .description(challenge.getDescription())
                    .startDate(challenge.getStartDate())
                    .endDate(challenge.getEndDate())
                    .memberCount(challenge.getMemberCount())
                    .bookDetail(bookDetail)
                    .build();
        }

    }
  
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
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer memberCount;
        private String category;
        private ChallengeStatus status;
        private BookResponse.Detail2 book;

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
                    .book(BookResponse.Detail2.from(challenge.getBook()))
                    .build();
        }
    }
}
