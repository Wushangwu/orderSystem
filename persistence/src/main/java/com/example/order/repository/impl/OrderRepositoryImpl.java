package com.example.order.repository.impl;

import com.example.order.domain.entity.Order;
import com.example.order.domain.repository.OrderRepository;
import com.example.order.persistence.DO.OrderDO;
import com.example.order.persistence.OrderMapper;
import com.example.order.persistence.OrderTranslater;
import com.example.order.types.UserId;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    public OrderMapper orderDao;

    public OrderTranslater orderTranslater;

    @Override
    public void save(Order order) throws Exception {
        OrderDO orderDO = orderTranslater.fromOrder(order);
        orderDao.save(orderDO);
        order.setId(orderDO.getId());
    }

    @Override
    public void taken(Order order) throws Exception {
        OrderDO orderDO = orderTranslater.fromOrder(order);
        orderDao.update(orderDO);
        order.setId(orderDO.getId());
    }

    @Override
    public List<Order> find(UserId userId) {
        return null;
    }
}
