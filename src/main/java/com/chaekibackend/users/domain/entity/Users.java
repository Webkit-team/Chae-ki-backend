package com.chaekibackend.users.domain.entity;

import com.chaekibackend.book.domain.entity.BookReview;
import com.chaekibackend.book.domain.entity.BookReviewScrap;
import com.chaekibackend.book.domain.entity.LikeBook;
import com.chaekibackend.chellenge.domain.entity.ChallengeMember;
import com.chaekibackend.users.api.request.UsersRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Setter
    private String imageUrl;

    private String role;

    private Integer point;

    private Integer reportCount;

    @Setter
    private Boolean expired;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChallengeMember> challengeList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LikeBook> likeList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BookReview> reviewList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BookReviewScrap> scrapList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Coupon> couponList;

    public static Users from (UsersRequest.Create user, String imageUrl) {
        return Users.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .imageUrl(imageUrl)
                .role("USER")
                .point(0)
                .reportCount(0)
                .expired(false)
                .build();
    }
}
