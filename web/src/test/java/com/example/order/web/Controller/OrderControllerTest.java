package com.example.order.web.Controller;

import com.example.order.config.RedisUtil;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.repository.OrderRepository;
import com.example.order.service.impl.OrderServiceImpl;
import com.example.order.web.DTO.LocationCoordinateDTO;
import com.example.order.web.DTO.OrderDTO;
import com.example.order.web.Exception.RedisLockedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @InjectMocks
    public OrderController orderController;

    @Mock
    public RedisUtil redisUtil;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception{
        orderController = new OrderController();
        MockitoAnnotations.initMocks(this);
        Field filed = OrderController.class.getDeclaredField("redisUtil");
        filed.setAccessible(true);
        filed.set(orderController,redisUtil);
    }

    @Test
    public void take_race_condition() throws Exception {
        thrown.expect(RedisLockedException.class);
        thrown.expectMessage("It is already taken");
        when(redisUtil.setnx(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenReturn(false);
        orderController.takenOrder("123",new OrderDTO());
    }

    @Test
    public void create_order_input_discorrect_length() throws Exception {
        thrown.expect(DisCorrectInputException.class);
        thrown.expectMessage("input error from user,please check it");
        LocationCoordinateDTO locationCoordinateDTO = new LocationCoordinateDTO();
        String[] origin = new String[1];
        String[] destination = new String[1];
        locationCoordinateDTO.setDestination(destination);
        locationCoordinateDTO.setOrigin(origin);
        orderController.createOrder(locationCoordinateDTO);
    }
}