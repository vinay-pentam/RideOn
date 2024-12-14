package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import com.sbpcrs.project.uber.uberapp.entities.Rider;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

public interface RideService {

    Ride updateRideStatus(Ride ride, RideStatus status);

    void matchWithDriver(RideRequestDTO rideRequestDTO);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);
}
