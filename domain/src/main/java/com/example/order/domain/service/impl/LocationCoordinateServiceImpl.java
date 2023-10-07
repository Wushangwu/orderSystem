package com.example.order.domain.service.impl;

import com.example.order.domain.entity.LocationCoordinate;
import com.example.order.domain.external.DistanceCalculateService;
import com.example.order.domain.service.LocationCoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationCoordinateServiceImpl implements LocationCoordinateService {

    @Autowired
    public DistanceCalculateService distanceCalculateService;

    @Override
    public double calculateDistance(LocationCoordinate locationCoordinate) throws IOException, InterruptedException {
         return distanceCalculateService.getDistance(locationCoordinate.getStartLatitude(),
                 locationCoordinate.getStartLongitude(),
                 locationCoordinate.getEndLatitude(), locationCoordinate.getEndLongitude());
    }
}
