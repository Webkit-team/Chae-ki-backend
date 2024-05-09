package com.chaekibackend.book.domain.entity;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReview {
    @Id
    @GeneratedValue
    private Long no;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    @Column(nullable=false, columnDefinition="BOOLEAN DEFAULT true")
    private Boolean visible;
    @ManyToOne
    @JoinColumn(name="uno")
    private Users users;
    @ManyToOne
    private Book book;
    @OneToMany(mappedBy = "bookReview", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BookReviewPhoto> photoList;

}
