package com.chaekibackend.users.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UsersRequest {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class Create {
        private String username;
        private String password;
        private String nickname;
        private String imageString;
    }
}
