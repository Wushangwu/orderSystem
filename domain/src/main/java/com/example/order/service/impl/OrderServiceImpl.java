package com.example.order.service.impl;

import com.example.order.constant.OrderStatus;
import com.example.order.entity.Order;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.exception.OrderException;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    public  OrderRepository orderRepository;

    @Override
    public boolean create(Order order) throws Exception {
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> find(Integer page,Integer limit) throws Exception {
        if(page < 1){
            throw new DisCorrectInputException("input page number is less than 1");
        }
        return orderRepository.find(page,limit);
    }

    @Override
    public boolean take(Order order) throws Exception {
        if(OrderStatus.TAKEN.equals(orderRepository.getStatus(order.getId()))){
            throw new OrderException("order is taken");
        }
        orderRepository.taken(order);
        return true;
    }

}
