package com.example.order.service.impl;

import com.example.order.entity.Coordinate;
import com.example.order.external.DistanceCalculateService;
import com.example.order.service.LocationCoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationCoordinateServiceImpl implements LocationCoordinateService {

    @Autowired
    public DistanceCalculateService distanceCalculateService;

    @Override
    public Integer calculateDistance(Coordinate coordinate) throws IOException, InterruptedException {
         return distanceCalculateService.getDistance(coordinate.getStartLatitude(),
                 coordinate.getStartLongitude(),
                 coordinate.getEndLatitude(), coordinate.getEndLongitude());
    }
}
