package com.example.order.web.DTO;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class OrderDTO {

    private String status;

    private String id;

    private String distance;
}
