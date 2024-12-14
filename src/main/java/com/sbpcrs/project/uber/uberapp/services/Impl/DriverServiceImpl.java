package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RiderDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.RideRequest;
import com.sbpcrs.project.uber.uberapp.entities.User;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.repositories.DriverRepository;
import com.sbpcrs.project.uber.uberapp.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService requestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest rideRequest = requestService.getRideRequestById(rideRequestId);

        if(!rideRequest.getRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Driver cannot accept the ride request which has status : "+rideRequest.getRequestStatus());
        }

        Driver driver = getCurrentDriver();
        if(!driver.getAvailable())
            throw new RuntimeException("Driver is not available to accept the ride request id : "+ rideRequestId);

        Driver savedDriver = updateAvailability(driver, false);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver()))
            throw new RuntimeException("Driver didn't match with the ride with id : "+ rideId +" to cancel the ride");

        if(!ride.getRideStatus().equals(RideStatus.CONFORMED))
            throw new RuntimeException("Cannot cancel the ride with id : "+ rideId + "having status : "+ ride.getRideStatus());

        ride.setRideStatus(RideStatus.CANCELLED);
        updateAvailability(driver, true);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver()))
            throw new RuntimeException("Driver didn't match with the ride with id : "+ rideId);

        if(!ride.getRideStatus().equals(RideStatus.CONFORMED))
            throw new RuntimeException("Cannot start the ride with id : "+ rideId + "having status : "+ ride.getRideStatus());

        if(!otp.equals(ride.getOtp()))
            throw new RuntimeException("Otp mismatch for ride with id : "+ rideId);

        ride.setStartTime(LocalDateTime.now());
        Ride updatedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        paymentService.createNewPayment(updatedRide);
        ratingService.createNewRating(updatedRide);

        return modelMapper.map(updatedRide, RideDTO.class);

    }

    @Override
    public RideDTO endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver()))
            throw new RuntimeException("Driver didn't match with the ride with id : "+ rideId);

        if(!ride.getRideStatus().equals(RideStatus.ONGOING))
            throw new RuntimeException("Cannot end the ride with id : "+ rideId + "having status : "+ ride.getRideStatus());

        ride.setEndTime(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        paymentService.processPayment(savedRide);
        updateAvailability(driver, true);

        return modelMapper.map(savedRide, RideDTO.class);

    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver()))
            throw new RuntimeException("Driver is not the owner of the ride with id : "+ rideId);

        if(!ride.getRideStatus().equals(RideStatus.ENDED))
            throw new RuntimeException("Cannot perform rating when ride id : "+ rideId + "having status : "+ ride.getRideStatus());

        return ratingService.rateRider(ride, rating);
    }

    @Override
    public DriverDTO getMyProfile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    public Page<RideDTO> listAllMyRides(PageRequest pageRequest) {
        Driver driver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Driver associated with user with id :"+user.getId()+" doesn't exist"));
    }

    @Override
    public Driver updateAvailability(Driver driver, boolean status) {
        driver.setAvailable(true);
        return driverRepository.save(driver);
    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
