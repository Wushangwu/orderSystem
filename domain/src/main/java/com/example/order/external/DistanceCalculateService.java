package com.example.order.external;


import java.io.IOException;

public interface DistanceCalculateService {
    Integer getDistance(String startLatitude, String startLongitude, String endLatitude, String endLongitude) throws IOException, InterruptedException;

}

