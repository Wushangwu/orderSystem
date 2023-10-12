package com.example.order.repository.impl;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.persistence.DO.OrderDO;
import com.example.order.persistence.mapper.OrderMapper;
import com.example.order.persistence.OrderTranslater;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Order> find(Integer page,Integer limit) {
        PageHelper.startPage(page, limit);
        List<OrderDO> orderDOS = orderDao.find();
        return orderDOS.stream().map(orderDO -> {
            try {
                return orderTranslater.toOrder(orderDO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public String getStatus(String id)  {
         return orderDao.getStatus(id);
    }

}
