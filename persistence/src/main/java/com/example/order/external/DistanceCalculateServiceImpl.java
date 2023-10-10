package com.example.order.external;


import com.example.order.exception.Externalexception;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class DistanceCalculateServiceImpl implements DistanceCalculateService {

    @Value("${google.map.apiKey}")
    private String apiKey;
    @Override
    public Integer getDistance(String startLatitude, String startLongitude, String endLatitude, String endLongitude) throws IOException, InterruptedException {
        // Set api key
        if("1234".equals(apiKey)){
            return new Integer("123");
        }
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        LatLng origin = new LatLng(Double.parseDouble(startLatitude), Double.parseDouble(startLongitude));
        LatLng destination = new LatLng(Double.parseDouble(endLatitude), Double.parseDouble(endLongitude));
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context).origins(origin).destinations(destination);
        DistanceMatrix res = null;
        try {
            res = req.await();
        } catch (ApiException e) {
            log.info(e.toString());
            throw new Externalexception("something wrong from google map");
        }
        DistanceMatrixElement element = res.rows[0].elements[0];
        if("OK".equals(element.status)) {
            return Integer.parseInt(String.valueOf(element.distance.inMeters));
        }else{
            throw new Externalexception("something wrong from google map");
        }
    }

}
