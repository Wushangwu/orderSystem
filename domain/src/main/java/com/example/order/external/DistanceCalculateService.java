package com.example.order.external;

import com.example.order.types.Currency;
import com.example.order.types.ExchangeRate;

import java.io.IOException;

public interface DistanceCalculateService {
    double getDistance(String startLatitude, String startLongitude, String endLatitude, String endLongitude) throws IOException, InterruptedException;

}

