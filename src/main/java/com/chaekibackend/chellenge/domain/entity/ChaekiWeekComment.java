package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Setter
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
