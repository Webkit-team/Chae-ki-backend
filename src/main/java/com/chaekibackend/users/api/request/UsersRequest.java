package com.chaekibackend.users.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UsersRequest {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String userId;
        private String password;
        private String nickname;
        private String imageString;
    }
}
