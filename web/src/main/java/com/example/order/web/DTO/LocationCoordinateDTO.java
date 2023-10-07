package com.example.order.web.DTO;

import lombok.Data;

@Data
public class LocationCoordinateDTO {

    private String[] origin;

    private String[] destination;
}
