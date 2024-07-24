package com.sbpcrs.project.uber.uberapp.entities;

import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickUpLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropUpLocation;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @Enumerated(value = EnumType.STRING)
    private RideRequestStatus requestStatus;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

}
