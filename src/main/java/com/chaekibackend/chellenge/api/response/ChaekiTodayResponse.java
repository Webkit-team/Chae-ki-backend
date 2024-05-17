package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import lombok.*;

import java.time.LocalDate;

public class ChaekiTodayResponse {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        private Long no;
        private String challengeName;
        private String bookName;
        private String content;
        private Integer readingPage;
        private Integer readingTime;
        private LocalDate createdAt;
        private Integer likeCount;
        private Boolean visible;

        public static ChaekiTodayResponse.Detail from(ChaekiToday chaekiToday){
            return Detail.builder()
                    .no(chaekiToday.getNo())
                    .challengeName(chaekiToday.getChallengeMember().getChallenge().getName())
                    .bookName(chaekiToday.getChallengeMember().getChallenge().getBook().getName())
                    .content(chaekiToday.getContent())
                    .readingPage(chaekiToday.getReadingPage())
                    .readingTime(chaekiToday.getReadingTime())
                    .createdAt(chaekiToday.getCreatedAt())
                    .likeCount(chaekiToday.getLikeCount())
                    .visible(chaekiToday.getVisible())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Creation {
        private Long todayNo;
        private Long userNo;
        private Long challengeNo;

        public static Creation from(ChaekiToday today){
            return Creation
                    .builder()
                    .todayNo(today.getNo())
                    .userNo(today
                            .getChallengeMember()
                            .getUsers()
                            .getNo())
                    .challengeNo(today
                            .getChallengeMember()
                            .getChallenge()
                            .getNo())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class TimeSave {
        private Long userNo;
        private Long challengeNo;
        private Long todayNo;

    }
}
