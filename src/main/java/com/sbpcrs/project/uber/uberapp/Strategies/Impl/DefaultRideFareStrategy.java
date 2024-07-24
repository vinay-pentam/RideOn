package com.sbpcrs.project.uber.uberapp.Strategies.Impl;

import com.sbpcrs.project.uber.uberapp.Strategies.RideFareCalculationStrategy;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import org.springframework.stereotype.Service;

@Service
public class DefaultRideFareStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
