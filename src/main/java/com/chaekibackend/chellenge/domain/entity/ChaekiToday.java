package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChaekiToday {
    @Id
    @GeneratedValue
    private Long no;

    private String content;

    @Setter
    private Integer readingPage;

    @Setter
    private Integer readingTime;

    private LocalDate createdAt;

    @Setter
    private Integer likeCount;

    private Boolean visible;

    @ManyToOne
    @JoinColumn(name="chaekiWeek_no")
    private ChaekiWeek chaekiWeek;

    @ManyToOne
    @JoinColumn(name="challengeMember_no")
    private ChallengeMember challengeMember;
}
