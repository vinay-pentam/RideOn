package com.sbpcrs.project.uber.uberapp.Strategies.Impl;

import com.sbpcrs.project.uber.uberapp.Strategies.DriverMatchingStrategy;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import com.sbpcrs.project.uber.uberapp.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NearestTopRatedDriverMatchingStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    public List<Driver> findDrivers(RideRequest rideRequest){
        return driverRepository.findNearByTopRatedDriver(rideRequest.getPickUpLocation());
    }
}
