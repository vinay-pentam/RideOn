package com.sbpcrs.project.uber.uberapp.DTO;

import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Rider;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {

    private Long id;

    private Point pickUpLocation;

    private Point dropUpLocation;

    private LocalDateTime createdTime;

    private RiderDTO rider;

    private DriverDTO driver;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private RideStatus requestStatus;

    private PaymentMethod paymentMethod;

}
