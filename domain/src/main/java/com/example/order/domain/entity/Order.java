package com.example.order.domain.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private String status;

    private String id;

    private String distance;

    /**
     * create the order
     *
     * @return isSuccess boolean
     */
    public boolean create(String distance) {
        boolean isSuccess = true;
        return isSuccess;
    }

    /**
     * get orders
     *
     * @return Orders List<Order>
     */
    public List<Order> find() {
        List<Order> Orders = new ArrayList<>();
        return Orders;
    }

    /**
     * take the order;
     *
     * @return Orders List<Order>
     */
    public boolean take() {
        boolean isSuccess = true;
        return isSuccess;
    }

}
