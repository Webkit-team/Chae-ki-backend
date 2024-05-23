package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeekComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class CommentResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Register {
        private Long no;
        private String content;
        private Boolean visible;

        public static Register from(ChaekiWeekComment comment) {
            return Register.builder()
                    .no(comment.getNo())
                    .content(comment.getContent())
                    .visible(comment.getVisible())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Like {
        private Long commentNo;
        private Integer likeCount;
        private Boolean visible;
        private LocalDate createdAt;

        public static Like from(ChaekiWeekComment comment) {
            return Like.builder()
                    .commentNo(comment.getNo())
                    .likeCount(comment.getLikeCount())
                    .visible(comment.getVisible())
                    .createdAt(comment.getCreatedAt().toLocalDate())
                    .build();
        }
    }
}
