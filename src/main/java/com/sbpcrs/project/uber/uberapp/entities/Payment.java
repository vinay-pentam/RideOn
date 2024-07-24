package com.sbpcrs.project.uber.uberapp.entities;

import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @OneToOne
    private Ride ride;

    @CreationTimestamp
    private LocalDateTime timeStamp;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
