package com.example.order.service;

import com.example.order.entity.Coordinate;

import java.io.IOException;

public interface CoordinateService {

    Integer calculateDistance(Coordinate coordinate) throws IOException, InterruptedException;
}
