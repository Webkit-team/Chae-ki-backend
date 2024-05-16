package com.chaekibackend.book.domain.entity;

import com.chaekibackend.chellenge.domain.entity.Challenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long no;
    private String name;
    private String category;
    private String writer;
    private String description;
    private Integer likeCount;
    private Integer pageNumber;
    private String publisher;
    private String imageUrl;
    private String shopUrl;
    private Integer price;
    private String isbnCode;
    private LocalDate publishDate;

    @Builder.Default
    @OneToMany(mappedBy = "book", cascade = {CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Challenge> challengeList = new ArrayList<>();
}
