package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChaekiWeek {
    @Id
    @GeneratedValue
    private Long no;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name="challenge_no")
    private Challenge challenge;
    @ManyToOne
    private ChallengeMember challengeMember;
    @OneToMany(mappedBy = "chaekiWeek", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiWeekComment> commentList;
    @OneToMany(mappedBy = "chaekiWeek", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiToday> todayList;

    public static ChaekiWeek createNewWeek(Challenge challenge, LocalDate start) {
        return ChaekiWeek
                .builder()
                .startDate(start)
                .endDate(start.plusDays(6))
                .challenge(challenge)
                .build();
    }
}
