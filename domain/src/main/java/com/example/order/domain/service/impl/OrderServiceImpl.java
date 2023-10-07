package com.example.order.domain.service.impl;

import com.example.order.domain.entity.Order;
import com.example.order.domain.repository.OrderRepository;
import com.example.order.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public  OrderRepository orderRepository;

    @Override
    public boolean create(Order order) throws Exception {
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> find(Integer page,Integer limit) throws Exception {
        return orderRepository.find(page,limit);
    }

    @Override
    public boolean take(String id) throws Exception {
        Order order = new Order();
        order.setId(id);
        orderRepository.taken(order);
        return true;
    }
}
