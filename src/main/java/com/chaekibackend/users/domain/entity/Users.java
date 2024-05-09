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

    private String role;

    private Integer point;

    private Integer totalReadingTime;

    private Integer reportCount;

    @Setter
    private String imageUrl;

    @Setter
    private Boolean expired;

    @OneToOne
    private UserImage userImage;

    @OneToMany
    private List<ChallengeMember> challengeList;

    @OneToMany
    private List<LikeBook> likeList;

    @OneToMany
    private List<BookReview> reviewList;

    @OneToMany
    private List<BookReviewScrap> scrapList;

    @OneToMany
    private List<Coupon> couponList;

    public static Users from (UsersRequest.Create user, String imageUrl) {
        return Users.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .role("USER")
                .point(0)
                .totalReadingTime(0)
                .reportCount(0)
                .imageUrl(imageUrl)
                .expired(false)
                .build();
    }
}
