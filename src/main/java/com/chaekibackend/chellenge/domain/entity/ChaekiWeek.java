package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class ChaekiWeek {
    @Id
    @GeneratedValue
    private Long no;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name="challenge_no")
    private Challenge challenge;

    @OneToMany(mappedBy = "chaekiWeek", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("likeCount DESC")
    private List<ChaekiWeekComment> commentList;

    @OneToMany(mappedBy = "chaekiWeek", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiToday> todayList;

    public Boolean included(LocalDate date) {
        boolean isAfter = startDate.minusDays(1).isBefore(date);
        boolean isBefore = endDate.plusDays(1).isAfter(date);

        return isAfter && isBefore;
    }
}
