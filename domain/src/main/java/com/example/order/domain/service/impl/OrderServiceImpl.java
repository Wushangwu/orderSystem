package com.example.order.domain.service.impl;

import com.example.order.domain.entity.Order;
import com.example.order.domain.repository.OrderRepository;
import com.example.order.domain.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    public  OrderRepository orderRepository;

    @Override
    public boolean create(String distance) throws Exception {
        Order order = new Order();
        order.setDistance(distance);
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> find() {
        return null;
    }

    @Override
    public boolean take(String id) throws Exception {
        Order order = new Order();
        order.setId(id);
        orderRepository.taken(order);
        return true;
    }
}
