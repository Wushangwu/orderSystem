package com.example.order.service;

import com.example.order.entity.Order;

import java.util.List;

public interface OrderService {

    /**
     * create the order
     *
     * @return isSuccess boolean
     */
    boolean create(Order order) throws Exception;

    /**
     * get orders
     *
     * @return Orders List<Order>
     */
    List<Order> find(Integer page,Integer limit) throws Exception;

    /**
     * take the order;
     *
     * @return Orders List<Order>
     */
    boolean take(Order order) throws Exception;

}
