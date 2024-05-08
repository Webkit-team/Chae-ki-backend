package com.chaekibackend.book.domain.entity;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewScrap {
    @Id
    @GeneratedValue
    private Long no;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private Users users;

}
