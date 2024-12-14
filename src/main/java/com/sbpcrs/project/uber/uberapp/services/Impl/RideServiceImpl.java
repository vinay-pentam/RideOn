package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import com.sbpcrs.project.uber.uberapp.entities.Rider;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.repositories.RideRepository;
import com.sbpcrs.project.uber.uberapp.services.RideRequestService;
import com.sbpcrs.project.uber.uberapp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final ModelMapper modelMapper;
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus status) {

        rideRepository.findById(ride.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ride doesn't exist with id : "+ ride.getId()));
        ride.setRideStatus(status);
        return rideRepository.save(ride);
    }

    @Override
    public void matchWithDriver(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
         return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFoundException("Ride doesn't exist with id : "+ rideId));
    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRequestStatus(RideRequestStatus.CONFORMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setId(null);
        ride.setRideStatus(RideStatus.CONFORMED);
        ride.setDriver(driver);
        ride.setOtp(generateOTP());
        ride.setRider(rideRequest.getRider());


        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    private String generateOTP(){
        Random random = new Random();
         int opt = random.nextInt(10000); // 0 to 9999
        return String.format("%04d", opt);
    }
}
