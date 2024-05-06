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
    private Long id;

    @Column(unique = true)
    @Setter
    private String userId;

    private String password;

    private String nickname;

    private String role;

    private Integer point;

    private String imageString;

    private Integer totalReadingTime;

    private Integer reportCount;

    public static Users from (UsersRequest.Create user) {
        return Users.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .imageString(user.getImageString())
                .role("USER")
                .point(0)
                .totalReadingTime(0)
                .reportCount(0)
                .build();
    }
}
