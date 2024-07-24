package com.sbpcrs.project.uber.uberapp.Strategies.Impl;

import com.sbpcrs.project.uber.uberapp.Strategies.DriverMatchingStrategy;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findDrivers(RideRequest rideRequest) {
        return Collections.emptyList();
    }
}
