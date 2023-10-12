package com.example.order.impl;

import com.example.order.config.RedisUtil;
import com.example.order.entity.Order;
import com.example.order.exception.OrderException;
import com.example.order.service.OrderService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.lang.reflect.Field;

import static org.mockito.Mockito.when;


public class OrderApplicationImplTest {
    @Resource
    public OrderApplicationImpl orderApplication;

    @Mock
    public RedisUtil redisUtil;

    @Mock
    public OrderService orderService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception{
        orderApplication = new OrderApplicationImpl();
        MockitoAnnotations.openMocks(this);
        Field filed = OrderApplicationImpl.class.getDeclaredField("redisUtil");
        filed.setAccessible(true);
        filed.set(orderApplication,redisUtil);

        Field filed2 = OrderApplicationImpl.class.getDeclaredField("orderService");
        filed2.setAccessible(true);
        filed2.set(orderApplication,orderService);
    }


    @Test
    public void take_order_is_taken_redis() throws Exception {
        thrown.expect(OrderException.class);
        thrown.expectMessage("Order is already taken");
        when(redisUtil.get("order_status_123")).thenReturn("taken");
        Order order = new Order();
        order.setId("123");
        orderApplication.takenOrder(order);
    }

    @Test
    public void take_order_is_not_exist() throws Exception {
        thrown.expect(OrderException.class);
        thrown.expectMessage("Order is not exit");
        when(redisUtil.get("order_status_123")).thenReturn(null);
        when(orderService.getStatus(Mockito.any())).thenReturn(null);
        Order order = new Order();
        order.setId("123");
        orderApplication.takenOrder(order);
    }

    @Test
    public void take_order_is_taken() throws Exception {
        thrown.expect(OrderException.class);
        thrown.expectMessage("Order is already taken");
        when(redisUtil.get("order_status_123")).thenReturn(null);
        when(orderService.getStatus(Mockito.any())).thenReturn("taken");
        when(redisUtil.setnx(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(true);
        Order order = new Order();
        order.setId("123");
        orderApplication.takenOrder(order);
    }


}
