package com.example.order.external;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class DistanceCalculateServiceImpl implements DistanceCalculateService {

    @Value("${google.map.apiKey}")
    private String apiKey;
    @Override
    public double getDistance(String startLatitude, String startLongitude, String endLatitude, String endLongitude) throws IOException, InterruptedException {
        // Set api key
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        // set origin and destination
        LatLng origin = new LatLng(Double.parseDouble(startLatitude), Double.parseDouble(startLongitude));
        LatLng destination = new LatLng(Double.parseDouble(endLatitude), Double.parseDouble(endLongitude));
        // 计算距离
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context).origins(origin).destinations(destination);
        DistanceMatrix res = null;
        try {
            res = req.await();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        DistanceMatrixElement element = res.rows[0].elements[0];
        if("OK".equals(element.status)) {
            return (double) element.distance.inMeters;
        }
        return -99;
    }

}
