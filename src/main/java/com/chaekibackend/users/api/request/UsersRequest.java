package com.chaekibackend.users.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UsersRequest {
    @Builder
    @Getter
    @AllArgsConstructor
    @Schema(name = "UsersRequest.Create")
    public static class Create {
        private String username;
        private String password;
        private String nickname;
        private String imageString;
    }
}
