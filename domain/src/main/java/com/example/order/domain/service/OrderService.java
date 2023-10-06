package com.example.order.domain.service;

import com.example.order.domain.entity.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    /**
     * create the order
     *
     * @return isSuccess boolean
     */
    boolean create(String distance) throws Exception;

    /**
     * get orders
     *
     * @return Orders List<Order>
     */
    List<Order> find();

    /**
     * take the order;
     *
     * @return Orders List<Order>
     */
    boolean take(String id) throws Exception;
}
