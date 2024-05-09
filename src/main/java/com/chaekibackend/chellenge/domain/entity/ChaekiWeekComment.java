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
    @JoinColumn(name="challengeMember_no")
    private ChallengeMember challengeMember;
    @ManyToOne
    @JoinColumn(name="chaekiWeek_no")
    private ChaekiWeek chaekiWeek;
}
