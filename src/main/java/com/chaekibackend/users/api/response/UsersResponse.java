package com.chaekibackend.users.api.response;

import com.chaekibackend.users.domain.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UsersResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Create {
        private Long uno;
        private String username;

        public static UsersResponse.Create from(Users user) {
            return Create.builder()
                    .uno(user.getId())
                    .username(user.getUsername())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Login {
        private Long uno;
        private String username;
        private String role;

        public static Login from(Users findUser) {
            return Login.builder()
                    .uno(findUser.getId())
                    .username(findUser.getUsername())
                    .role(findUser.getRole())
                    .build();
        }
    }
}
