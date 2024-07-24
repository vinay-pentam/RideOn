package com.sbpcrs.project.uber.uberapp.DTO;

import com.sbpcrs.project.uber.uberapp.entities.Rider;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {

    private Long id;

    private Point pickUpLocation;

    private Point dropUpLocation;

    private LocalDateTime requestedTime;

    private RiderDTO rider;

    private RideRequestStatus requestStatus;

    private PaymentMethod paymentMethod;
}
