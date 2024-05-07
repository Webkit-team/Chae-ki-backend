package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChaekiToday {
    @Id
    @GeneratedValue
    private Long no;
    private String content;
    private LocalDateTime createdAt;
    private Integer likeCount;
    private Boolean visible;
    @ManyToOne
    @JoinColumn(name="CHAEKIWEEK_ID")
    private ChaekiWeek chaekiWeek;
    @ManyToOne
    @JoinColumn(name="CHALLENGEMEMBER_ID")
    private ChallengeMember challengeMember;
}
