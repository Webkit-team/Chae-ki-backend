package com.chaekibackend.book.domain.entity;

import jakarta.annotation.ManagedBean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewPhoto {
    @Id
    @GeneratedValue
    private Long no;
    private String imageString;
    @ManyToOne
    @JoinColumn(name="bookReview_no")
    private BookReview bookReview;  // 이것도 OneToMany로 리스트 갖고 있어야 할 듯
}
