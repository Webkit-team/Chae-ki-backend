package com.chaekibackend.chellenge.api.request;

import com.chaekibackend.book.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChallengeRequest {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class Create{
        private Long no;
        private String name;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer memberCount;
        private Long bookNo;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Update{
        private Long no;
        private String name;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer memberCount;
        private Book book;
    }
}
