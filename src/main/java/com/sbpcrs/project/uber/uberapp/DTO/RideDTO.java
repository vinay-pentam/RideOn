package com.sbpcrs.project.uber.uberapp.DTO;

import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data

public class RideDTO {

    private Long id;

    private String otp;

    private PointDTO pickUpLocation;

    private PointDTO dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDTO rider;

    private DriverDTO driver;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private RideStatus rideStatus;

    private Double fare;

    private PaymentMethod paymentMethod;

}
