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
    @JoinColumn(name="uno")
    private Users users;
    @ManyToOne
    @JoinColumn(name="challenge_no")
    private Challenge challenge;
    @OneToMany(mappedBy = "challengeMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiToday> todayList;
    @OneToMany(mappedBy = "challengeMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiWeekComment> commentList;
}
