package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
