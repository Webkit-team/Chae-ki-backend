package com.chaekibackend.users.domain.entity;

import com.chaekibackend.users.api.request.UsersRequest;
import jakarta.persistence.*;
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
