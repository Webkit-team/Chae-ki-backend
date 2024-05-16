package com.chaekibackend.chellenge.api.request;

import com.chaekibackend.book.domain.entity.Book;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ChallengeRequest {
    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "ChallengeRequest.Join")
    public static class Join {
        private Long cno;
        private Long uno;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @Schema(name = "ChallengeRequest.Create")
    public static class Create{
        private Long no;
        private String name;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer memberCount;
        private Book book;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @Schema(name = "ChallengeRequest.Update")
    public static class Update{
        private Long no;
        private String name;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer memberCount;
        private Book book;
    }
}
