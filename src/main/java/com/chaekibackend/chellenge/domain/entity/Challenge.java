package com.chaekibackend.chellenge.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="challenge")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Challenge {
    @Id
    @GeneratedValue
    private long id;
}
