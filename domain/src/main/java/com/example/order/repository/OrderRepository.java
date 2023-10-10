package com.example.order.repository;

import com.example.order.entity.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order) throws Exception;
    void taken(Order order) throws Exception;
    List<Order> find(Integer page,Integer limit) throws Exception;
    String getStatus(String id) throws Exception;
}
