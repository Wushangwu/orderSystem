package com.example.order.service.impl;

import com.example.order.entity.Order;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.repository.OrderRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void find_error_page() throws Exception {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input page number is less than 1");
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.find(-1,0);
    }

}