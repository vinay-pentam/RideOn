package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.repositories.RideRequestRepository;
import com.sbpcrs.project.uber.uberapp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest getRideRequestById(Long id) {
        return rideRequestRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("RideRequest doesn't exist with id "+ id)
        );
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Ride Request doesn't exist with id : " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
