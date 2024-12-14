package com.sbpcrs.project.uber.uberapp.Strategies;

import com.sbpcrs.project.uber.uberapp.Strategies.Impl.DefaultRideFareStrategy;
import com.sbpcrs.project.uber.uberapp.Strategies.Impl.NearestDriverMatchingStrategy;
import com.sbpcrs.project.uber.uberapp.Strategies.Impl.NearestTopRatedDriverMatchingStrategy;
import com.sbpcrs.project.uber.uberapp.Strategies.Impl.SurgeFactorRideFareStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    public final DefaultRideFareStrategy defaultRideFareStrategy;
    public final SurgeFactorRideFareStrategy surgeFactorRideFareStrategy;
    public final NearestDriverMatchingStrategy nearestDriverMatchingStrategy;
    public final NearestTopRatedDriverMatchingStrategy nearestTopRatedDriverMatchingStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double rating){

        if(rating < 4.8){
            return nearestDriverMatchingStrategy;
        }else{
            return nearestTopRatedDriverMatchingStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){

        //Surge time
        // 6PM to 9pm
        LocalTime surgeStartTime = LocalTime.of(18,00);
        LocalTime surgeEndTime = LocalTime.of(21,00);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return surgeFactorRideFareStrategy;
        }else{
            return defaultRideFareStrategy;
        }

    }

}
