package com.chaekibackend.chellenge.domain.entity;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChaekiWeek> weekList;
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChallengeMember> memberList;

    public static Challenge from (ChallengeRequest.Create challenge){
        return Challenge.builder()
                .no(challenge.getNo())
                .name(challenge.getName())
                .description(challenge.getDescription())
                .startDate(challenge.getStartDate())
                .endDate(challenge.getEndDate())
                .memberCount(challenge.getMemberCount())
//                .category (challenge.)
//                .status(challenge.)
                .book(challenge.getBook())
                .build();
    }
}
