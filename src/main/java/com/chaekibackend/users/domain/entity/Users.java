package com.chaekibackend.users.domain.entity;

import com.chaekibackend.users.api.request.UsersRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
public class Users {
    @Id
    @GeneratedValue
    private Long no;

    @Column(unique = true)
    private String username;

    @Setter
    private String password;

    @Setter
    private String nickname;

    private String role;

    private Integer point;

    @Setter
    private String imageUrl;

    private Integer totalReadingTime;

    private Integer reportCount;

    public static Users from (UsersRequest.Create user) {
        return Users.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .imageUrl(user.getImageUrl())
                .role("USER")
                .point(0)
                .totalReadingTime(0)
                .reportCount(0)
                .build();
    }
}
