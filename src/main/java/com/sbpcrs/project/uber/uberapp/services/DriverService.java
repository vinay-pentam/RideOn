package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideDTO;

import java.util.List;


public interface DriverService {

    RideDTO acceptRide(Long rideId);

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId);

    RideDTO endRide(Long rideId);

    RideDTO rateRider(Long rideId, Integer rating);

    DriverDTO driverProfile();

    List<RideDTO> listAllMyRides();
}
