package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.DTO.RideDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RiderDTO;

import java.util.List;

public interface RiderService {

    RideRequestDTO requestRide();

    RideDTO cancelRide(Long rideId);

    RideDTO rateDriver(Long rideId, Integer rating);

    RiderDTO riderProfile();

    List<RideDTO> listAllMyRides();

}
