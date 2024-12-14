package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.entities.RideRequest;

public interface RideRequestService {

    RideRequest getRideRequestById(Long id);

    void update(RideRequest rideRequest);
}
