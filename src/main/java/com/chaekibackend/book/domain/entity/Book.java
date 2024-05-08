package com.chaekibackend.book.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime publishDate;
}
