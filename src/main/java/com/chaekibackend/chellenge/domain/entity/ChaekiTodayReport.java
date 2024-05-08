package com.chaekibackend.chellenge.domain.entity;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChaekiTodayReport {
    @Id
    @GeneratedValue
    private Long no;
    private LocalDateTime createdAt;
    @ManyToOne
    private Users users;
    @ManyToOne
    private ChaekiToday chaekiToday;
}
