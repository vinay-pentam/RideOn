package com.sbpcrs.project.uber.uberapp.entities;

import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickUpLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropUpLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @CreationTimestamp
    private LocalDateTime startTime;

    @CreationTimestamp
    private LocalDateTime endTime;

    @Enumerated(value = EnumType.STRING)
    private RideStatus requestStatus;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String otp;

    private Double fare;

}
