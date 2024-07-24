package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.services.DistanceService;
import org.locationtech.jts.algorithm.Distance;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        return 0;
    }
}

