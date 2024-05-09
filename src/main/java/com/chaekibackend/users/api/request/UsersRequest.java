package com.chaekibackend.users.api.request;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UsersRequest {
    @Builder
    @Getter
    @AllArgsConstructor
    @Schema(name = "UsersRequest.Update")
    public static class Update {
        private String password;
        private String nickname;
        private String imageUrl;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @Schema(name = "UsersRequest.Create")
    public static class Create {
        private String username;
        private String password;
        private String nickname;
        private MultipartFile image;
    }
}
