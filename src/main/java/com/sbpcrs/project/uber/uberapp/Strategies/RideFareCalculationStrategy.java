package com.sbpcrs.project.uber.uberapp.Strategies;

import com.sbpcrs.project.uber.uberapp.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
