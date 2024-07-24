package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.RideDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RiderDTO;
import com.sbpcrs.project.uber.uberapp.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    @Override
    public RideRequestDTO requestRide() {
        return null;
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO riderProfile() {
        return null;
    }

    @Override
    public List<RideDTO> listAllMyRides() {
        return Collections.emptyList();
    }
}
