package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChaekiWeekComment {
    @Id
    @GeneratedValue
    private Long no;

    private String content;

    private Integer likeCount;

    private Boolean visible;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="challengeMember_no")
    private ChallengeMember challengeMember;

    @ManyToOne
    @JoinColumn(name="chaekiWeek_no")
    private ChaekiWeek chaekiWeek;
}
