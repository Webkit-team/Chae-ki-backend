package com.chaekibackend.users.api.response;

import com.chaekibackend.users.domain.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UsersResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Update {
        private Long no;
        private String username; // user id
        private String nickname;
        private String imageString;

        public static UsersResponse.Update from(Users user) {
            return UsersResponse.Update.builder()
                    .no(user.getNo())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .imageString(user.getImageString())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "UsersResponse.Create")
    public static class Create {
        private Long no;
        private String username;

        public static UsersResponse.Create from(Users user) {
            return Create.builder()
                    .no(user.getNo())
                    .username(user.getUsername())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Login {
        private Long no;
        private String username;
        private String role;

        public static Login from(Users findUser) {
            return Login.builder()
                    .no(findUser.getNo())
                    .username(findUser.getUsername())
                    .role(findUser.getRole())
                    .build();
        }
    }
}
