package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {
    @Override
    public Ride updateRideStatus(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDriver(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver) {
        return null;
    }
}
