package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChaekiWeekComment {
    @Id
    @GeneratedValue
    private Long no;
    private String content;
    private Integer likeCount;
    private Boolean visible;
    @ManyToOne
    @JoinColumn(name="CHALLENGEMEMBER_ID")
    private ChallengeMember challengeMember;
    @ManyToOne
    @JoinColumn(name="CHAEKIWEEK_ID")
    private ChaekiWeek chaekiWeek;
}
