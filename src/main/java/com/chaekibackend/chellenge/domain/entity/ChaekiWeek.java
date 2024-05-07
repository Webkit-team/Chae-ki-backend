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
    @ManyToOne
    @JoinColumn(name="CHALLENGE_ID")
    private Challenge challenge;
    @ManyToOne
    private ChallengeMember challengeMember;
    @OneToMany
    private List<ChaekiWeekComment> commentList;
    @OneToMany
    private List<ChaekiToday> todayList;
}
