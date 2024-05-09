package com.chaekibackend.users.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    private Long no;
    private String serialNumber;
    private float discountRate;
    private String status;
    private LocalDateTime expiredDate;
    @ManyToOne
    @JoinColumn(name="uno")
    private Users users;

}
