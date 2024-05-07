package com.chaekibackend.chellenge.domain.entity;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeMember {
    @Id
    @GeneratedValue
    private Long no;
    private Integer readingPage;
    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private Users users;
    @ManyToOne
    @JoinColumn(name="CHALLENGE_ID")
    private Challenge challenge;
    @OneToMany
    private List<ChaekiToday> todayList;
    @OneToMany
    private List<ChaekiWeekComment> commentList;
}
