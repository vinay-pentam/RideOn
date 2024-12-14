package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RideRequestDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RiderDTO;
import com.sbpcrs.project.uber.uberapp.Strategies.RideStrategyManager;
import com.sbpcrs.project.uber.uberapp.entities.*;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideRequestStatus;
import com.sbpcrs.project.uber.uberapp.entities.enums.RideStatus;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.repositories.RideRequestRepository;
import com.sbpcrs.project.uber.uberapp.repositories.RiderRepository;
import com.sbpcrs.project.uber.uberapp.services.DriverService;
import com.sbpcrs.project.uber.uberapp.services.RatingService;
import com.sbpcrs.project.uber.uberapp.services.RideService;
import com.sbpcrs.project.uber.uberapp.services.RiderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class RiderServiceImpl implements RiderService {

    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper mapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = mapper.map(rideRequestDTO, RideRequest.class);
        log.info(rideRequest.toString());
        rideRequest.setRequestStatus(RideRequestStatus.PENDING);
        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        rideRequest.setRider(rider);
        RideRequest savedRiderequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findDrivers(rideRequest);
        log.info("{}",drivers.getFirst().getId());
        // todo: send notification to all the available drivers
        return mapper.map(rideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
       Ride ride = rideService.getRideById(rideId);
       Rider rider = getCurrentRider();

       if(!rider.equals(ride.getRider()))
           throw new RuntimeException("Rider is not allowed to cancel the ride has id : "+ rideId);

       if(!ride.getRideStatus().equals(RideStatus.CONFORMED))
           throw new RuntimeException("Cannot cancel the ride when it is : "+ride.getRideStatus());

       driverService.updateAvailability(ride.getDriver(), true);
       Ride saveRide = rideService.updateRideStatus(ride, RideStatus.CONFORMED);
       return mapper.map(saveRide, RideDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();

        if(!rider.equals(ride.getRider()))
            throw new RuntimeException("Rider is not the owner of the ride with id : "+ rideId);

        if(!ride.getRideStatus().equals(RideStatus.ENDED))
            throw new RuntimeException("Cannot perform rating when ride id : "+ rideId + "having status : "+ ride.getRideStatus());

        return ratingService.rateDriver(ride, rating);
    }

    @Override
    public RiderDTO getProfile() {
        Rider rider = getCurrentRider();
        return mapper.map(rider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> listAllMyRides(PageRequest pageRequest) {

        Rider rider = getCurrentRider();
        return rideService.getAllRidesOfRider(rider, pageRequest).map(
                ride -> mapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public void createRider(User user) {
        Rider rider = Rider.builder()
                        .user(user).rating(0.0)
                        .build();
        riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return riderRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Rider associated with user with id :"+user.getId()+" doesn't exist"));
    }
}
