package com.chaekibackend.chellenge.api.response;

import com.chaekibackend.chellenge.domain.entity.ChaekiWeekComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
}
