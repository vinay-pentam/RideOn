package com.sbpcrs.project.uber.uberapp.Strategies;

import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;

import java.util.List;


public interface DriverMatchingStrategy {
    List<Driver> findDrivers(RideRequest rideRequest);
}
