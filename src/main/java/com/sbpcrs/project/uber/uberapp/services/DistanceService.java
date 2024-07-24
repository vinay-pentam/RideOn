package com.sbpcrs.project.uber.uberapp.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

    double calculateDistance(Point src, Point dest);
}
