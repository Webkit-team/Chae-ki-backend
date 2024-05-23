package com.chaekibackend.chellenge.api.request;

import lombok.Getter;

public class CommentRequest {
    @Getter
    public static class Register {
        private String content;
    }

    @Getter
    public static class Like {
        private Boolean liked;
    }
}
