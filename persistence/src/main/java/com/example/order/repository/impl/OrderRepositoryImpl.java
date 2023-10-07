package com.example.order.repository.impl;

import com.example.order.domain.entity.Order;
import com.example.order.domain.repository.OrderRepository;
import com.example.order.persistence.DO.OrderDO;
import com.example.order.persistence.OrderMapper;
import com.example.order.persistence.OrderTranslater;
import com.example.order.types.UserId;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    public OrderMapper orderDao;

    @Autowired
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
    public List<Order> find(Integer page,Integer limit) throws Exception {
        PageHelper.startPage(page, limit);
        List<OrderDO> orderDOS = orderDao.find();
        List<Order> orders = new ArrayList<>();
        for(OrderDO orderDO :orderDOS){
            Order order = orderTranslater.toOrder(orderDO);
            orders.add(order);
        }
        return orders;
    }
}
