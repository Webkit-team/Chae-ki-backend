package com.chaekibackend.book.domain.entity;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeBook {
    @Id
    @GeneratedValue
    private Long no;
    @Setter
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name="uno")
    private Users users;
    @ManyToOne
    private Book book;
}
