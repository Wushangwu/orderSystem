package com.example.order.entity;


import com.example.order.exception.DisCorrectInputException;
import lombok.Data;

@Data
public class Coordinate {

    private final String startLatitude;
    private final String startLongitude;
    private final String endLatitude;
    private final String endLongitude;
    private int distance;

    private static final double MaxLatitude = 80;
    private static final double MinLatitude = -80;
    private static final double MaxLongitude = 180;
    private static final double MinLongitude = -180;


    public Coordinate(String startLatitude, String startLongitude, String endLatitude, String endLongitude) {
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.checkLatitude(this.startLatitude);
        this.checkLongitude(this.startLongitude);
        this.checkLatitude(this.endLatitude);
        this.checkLongitude(this.endLongitude);
    }


    /**
     * check the input Latitude;
     *
     * @param latitude String
     */
    public void checkLatitude(String latitude){
        int i = Double.valueOf(latitude).compareTo(MaxLatitude);
        int b = Double.valueOf(latitude).compareTo(MinLatitude);
         if( Double.valueOf(latitude).compareTo(MaxLatitude) > 0 || Double.valueOf(latitude).compareTo(MinLatitude) < 0){
             throw(new DisCorrectInputException("input latitude is not correct"));
         };
    }

    /**
     * check the input Longitude;
     *
     * @param longitude String
     */
    public void checkLongitude(String longitude){
        if(Double.valueOf(longitude).compareTo(MaxLongitude) > 0 || Double.valueOf(longitude).compareTo(MinLongitude) < 0 ){
            throw(new DisCorrectInputException("input longitude is not correct"));
        };
    }

}
