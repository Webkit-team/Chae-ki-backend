package com.chaekibackend.users.api.response;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.users.domain.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class UsersResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "UsersResponse.Detail")
    public static class Detail {
        private Long no;
        private String username;
        private String nickname;
        private String password;
        private String imageUrl;
        private Integer point;
        private Integer totalReadingTime;
        private Boolean expired;

        public static UsersResponse.Detail from(Users user) {


            return Detail.builder()
                    .no(user.getNo())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .imageUrl(user.getImageUrl())
                    .point(user.getPoint())
                    .expired(user.getExpired())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "UsersResponse.Delete")
    public static class Delete {
        private Long no;
        private String username; // user id

        public static UsersResponse.Delete from(Users user) {
            return UsersResponse.Delete.builder()
                    .no(user.getNo())
                    .username(user.getUsername())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(name = "UsersResponse.Update")
    public static class Update {
        private Long no;
        private String username; // user id
        private String nickname;
        private String imageUrl;

        public static UsersResponse.Update from(Users user) {
            return UsersResponse.Update.builder()
                    .no(user.getNo())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .imageUrl(user.getImageUrl())
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
    @Schema(name = "UsersResponse.Login")
    public static class Login {
        private Long no;
        private String username;
        private String role;
        private Boolean expired;

        public static Login from(Users findUser) {
            return Login.builder()
                    .no(findUser.getNo())
                    .username(findUser.getUsername())
                    .role(findUser.getRole())
                    .expired(findUser.getExpired())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Duplication {
        private Boolean available;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class FavoriteBooks {
        private Long userNo;
        private String nickName;
        private List<FavoriteBookDetail> books;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class FavoriteBookDetail {
        private Long bookNo;
        private String title;
        private String imageUrl;

        public static FavoriteBookDetail from (Book book) {
            return FavoriteBookDetail.builder()
                    .bookNo(book.getNo())
                    .title(book.getName())
                    .imageUrl(book.getImageUrl())
                    .build();
        }
    }
}
