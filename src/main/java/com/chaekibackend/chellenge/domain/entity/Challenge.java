package com.chaekibackend.chellenge.domain.entity;

import com.chaekibackend.book.domain.entity.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="challenge")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Challenge {
    @Id
    @GeneratedValue
    private Long no;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer memberCount;
    private String category;
    @Enumerated(EnumType.STRING)
    private ChallengeStatus status;

    @ManyToOne
    private Book book;

    @OneToMany
    private List<ChaekiWeek> weekList;
    @OneToMany
    private List<ChallengeMember> memberList;

}
