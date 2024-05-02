package com.chaekibackend.users.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
public class Users {
    @Id @GeneratedValue
    private long id;
}
