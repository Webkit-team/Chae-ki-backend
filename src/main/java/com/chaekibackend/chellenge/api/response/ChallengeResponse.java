package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.book.api.response.BookResponse;
import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.chellenge.domain.entity.Challenge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        private BookResponse.Detail bookDetail;          // BookResponse로 바꿔야 함
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
}
