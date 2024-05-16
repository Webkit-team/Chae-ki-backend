package com.chaekibackend.chellenge.api.request;

import com.chaekibackend.book.domain.entity.Book;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ChallengeRequest {
    @Getter
    @Schema(name = "")
    public static class TimerSave {
        private Integer time;
    }

    @Getter
    @Schema(name = "ChallengeRequest.TodaySave")
    public static class TodaySave {
        private String content;
//        private
    }

    @Getter
    @Schema(name = "ChallengeRequest.Join")
    public static class Join {
        private Long cno;
        private Long uno;
    }

    @Getter
    @Schema(name = "ChallengeRequest.Create")
    public static class Create{
        private Long no;
        private String name;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer memberCount;
        private Long bookNo;
    }

    @Getter
    @Schema(name = "ChallengeRequest.Update")
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
