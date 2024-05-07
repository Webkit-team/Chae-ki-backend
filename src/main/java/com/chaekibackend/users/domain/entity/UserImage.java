package com.chaekibackend.users.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserImage {
    @Id
    @GeneratedValue
    private Long no;
    private String imageString;
    @OneToOne(mappedBy = "userImage")
    private Users users;

}
