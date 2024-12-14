package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.RiderDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Rating;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.Rider;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.exceptions.RuntimeConflictException;
import com.sbpcrs.project.uber.uberapp.repositories.DriverRepository;
import com.sbpcrs.project.uber.uberapp.repositories.RatingRepository;
import com.sbpcrs.project.uber.uberapp.repositories.RiderRepository;
import com.sbpcrs.project.uber.uberapp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating objRating =  ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("Rating object not found for ride with id : "+ride.getId())
        );

        if(objRating.getDriverRating() != null) throw new RuntimeConflictException("Driver has already been rated ");

        objRating.setDriverRating(rating);
        ratingRepository.save(objRating);

        Double avgRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);

        driver.setRating(avgRating);
        Driver savedDriver  = driverRepository.save(driver);

        return modelMapper.map(driver, DriverDTO.class);

    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating objRating =  ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("Rating object not found for ride with id : "+ride.getId())
        );

        if(objRating.getRiderRating() != null) throw new RuntimeConflictException("Rider has been already rated ");

        objRating.setRiderRating(rating);
        ratingRepository.save(objRating);

        Double avgRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);

        rider.setRating(avgRating);
        Rider savedRider  = riderRepository.save(rider);

        return modelMapper.map(rider, RiderDTO.class);

    }

    @Override
    public void createNewRating(Ride ride) {

        Rating rating = Rating.builder()
                .ride(ride)
                .driver(ride.getDriver())
                .rider(ride.getRider())
                .build();

        ratingRepository.save(rating);
    }
}
