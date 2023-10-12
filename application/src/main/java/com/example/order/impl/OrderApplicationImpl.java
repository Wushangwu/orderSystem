package com.example.order.impl;

import com.example.order.OrderApplication;
import com.example.order.config.RedisUtil;
import com.example.order.constant.OrderStatus;
import com.example.order.entity.Coordinate;
import com.example.order.entity.Order;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.exception.OrderException;
import com.example.order.service.CoordinateService;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationImpl implements OrderApplication {

    @Autowired
    public OrderService orderService;

    @Autowired
    public CoordinateService coordinateService;

    @Autowired
    public RedisUtil redisUtil;

    @Override
    public Order createOrder(Coordinate coordinate) throws Exception {
        Order order = new Order();
        order.setStatus(OrderStatus.UNASSIGNED);
        order.setDistance(String.valueOf(coordinateService.calculateDistance(coordinate)));
        orderService.create(order);
        return order;
    }

    @Override
    public Order takenOrder(Order order) throws Exception {
        if(OrderStatus.TAKEN.equals(redisUtil.get("order_status_" + order.getId()))){
            throw new OrderException("Order is already taken");
        }
        String status = orderService.getStatus(order);
        if(status == null){
            throw new OrderException("Order is not exit");
        }
        if(OrderStatus.TAKEN.equals(status)){
            redisUtil.setnx("order_status_" + order.getId(),OrderStatus.TAKEN,120L);
            throw new OrderException("Order is already taken");
        }
        if(!redisUtil.setnx("order_taken_" + order.getId(),"success", 60L)){
            throw new OrderException("Order is locked");
        }
        orderService.take(order);
        return null;
    }
}
