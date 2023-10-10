package com.example.order.service.impl;

import com.example.order.entity.Order;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.exception.OrderException;
import com.example.order.repository.OrderRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception{
        orderService = new OrderServiceImpl();
        MockitoAnnotations.initMocks(this);
        Field filed = OrderServiceImpl.class.getDeclaredField("orderRepository");
        filed.setAccessible(true);
        filed.set(orderService,orderRepository);
    }

    @Test
    public void find_error_page() throws Exception {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input page number is less than 1");
        orderService.find(-1,0);
    }

    @Test
    public void take_again() throws Exception {
        thrown.expect(OrderException.class);
        thrown.expectMessage("order is taken");
        when(orderRepository.getStatus(Mockito.anyString())).thenReturn("taken");
        Order order = new Order();
        order.setId("123");
        orderService.take(order);
    }

}