package com.example.order.domain.service;

import com.example.order.domain.entity.LocationCoordinate;

import java.io.IOException;

public interface LocationCoordinateService {

    double calculateDistance(LocationCoordinate locationCoordinate) throws IOException, InterruptedException;
}
