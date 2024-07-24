package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideDTO;
import com.sbpcrs.project.uber.uberapp.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public RideDTO acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDTO driverProfile() {
        return null;
    }

    @Override
    public List<RideDTO> listAllMyRides() {
        return Collections.emptyList();
    }
}
