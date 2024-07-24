package com.sbpcrs.project.uber.uberapp.Strategies;

import com.sbpcrs.project.uber.uberapp.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequest rideRequest);
}
