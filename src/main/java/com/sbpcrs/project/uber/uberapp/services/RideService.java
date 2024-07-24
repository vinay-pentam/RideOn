package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride updateRideStatus(Long rideId);

    void matchWithDriver(RideRequestDTO rideRequestDTO);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver);
}
