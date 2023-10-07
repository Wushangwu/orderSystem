package com.example.order.persistence.impl;

import com.example.order.domain.entity.Order;
import com.example.order.persistence.DO.OrderDO;
import com.example.order.persistence.OrderTranslater;
import org.springframework.stereotype.Service;

@Service
public class OrderTranslaterImpl implements OrderTranslater {

    @Override
    public Order toOrder(OrderDO orderDO) throws Exception {
        Order order = new Order();
        order.setDistance(orderDO.getDistance());
        order.setId(orderDO.getId());
        order.setStatus(orderDO.getStatus());
        return order;
    }

    @Override
    public OrderDO fromOrder(Order order) {
        OrderDO orderDo = new OrderDO();
        orderDo.setDistance(order.getDistance());
        orderDo.setId(order.getId());
        orderDo.setStatus(order.getStatus());
        return orderDo;
    }
}
