package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

public class ChallengeResponse {
    @Builder
    @AllArgsConstructor
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
