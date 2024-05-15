package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiToday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ChaekiTodayResponse {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        private Long no;
        private String content;
        private Integer readingPage;
        private Integer readingTime;
        private LocalDateTime createdAt;
        private Integer likeCount;
        private Boolean visible;

        public static ChaekiTodayResponse.Detail from(ChaekiToday chaekiToday){
            return Detail.builder()
                    .no(chaekiToday.getNo())
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
