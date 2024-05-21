package com.chaekibackend.chellenge.domain.entity;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @Setter
    private LocalDate startDate;
    @Setter
    private LocalDate endDate;
    private Integer memberCount;
    private String category;
    @Enumerated(EnumType.STRING)
    private ChallengeStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("startDate ASC")
    private List<ChaekiWeek> weekList;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChallengeMember> memberList;

    public static Challenge from (ChallengeRequest.Create challenge, Book book){
        return Challenge.builder()
                .name(challenge.getName())
                .description(challenge.getDescription())
                .startDate(challenge.getStartDate())
                .endDate(challenge.getEndDate())
                .memberCount(0)
                .category (book.getCategory())
                .status(ChallengeStatus.RECRUITING)
                .book(book)
                .build();
    }
}
