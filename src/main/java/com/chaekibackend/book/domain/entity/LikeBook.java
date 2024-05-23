package com.chaekibackend.book.domain.entity;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
