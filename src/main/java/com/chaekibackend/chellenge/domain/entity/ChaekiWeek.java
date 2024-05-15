package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChaekiWeek {
    @Id
    @GeneratedValue
    private Long no;
    private Integer readingPage;
    private Integer readingTime;
    @ManyToOne
    @JoinColumn(name="challenge_no")
    private Challenge challenge;
    @ManyToOne
    private ChallengeMember challengeMember;
    @OneToMany(mappedBy = "chaekiWeek", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiWeekComment> commentList;
    @OneToMany(mappedBy = "chaekiWeek", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiToday> todayList;
}
