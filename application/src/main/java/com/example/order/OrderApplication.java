package com.example.order;

import com.example.order.entity.Coordinate;
import com.example.order.entity.Order;

public interface OrderApplication {

    /**
     * create the order
     *
     * @return isSuccess boolean
     */
    Order createOrder(Coordinate coordinate) throws Exception;

    /**
     * create the order
     *
     * @return isSuccess boolean
     */
    Order takenOrder(Order order) throws Exception;
}
