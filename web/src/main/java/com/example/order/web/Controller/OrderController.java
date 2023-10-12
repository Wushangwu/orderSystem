package com.example.order.web.Controller;

import com.example.order.OrderApplication;
import com.example.order.config.RedisUtil;
import com.example.order.constant.OrderStatus;
import com.example.order.entity.Coordinate;
import com.example.order.entity.Order;
import com.example.order.exception.OrderException;
import com.example.order.service.CoordinateService;
import com.example.order.service.OrderService;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.web.DTO.LocationCoordinateDTO;
import com.example.order.web.DTO.OrderDTO;
import com.example.order.web.Exception.RedisLockedException;
import com.example.order.web.Response.RequestAdivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    public OrderApplication orderApplication;

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public OrderService orderService;

    @PostMapping()
    @RequestAdivce
    public Order createOrder(@RequestBody LocationCoordinateDTO locationCoordinateDTO) throws Exception {
        if(locationCoordinateDTO.getOrigin().length != 2 || locationCoordinateDTO.getDestination().length != 2  ){
            throw new DisCorrectInputException("input error from user,please check it");
        }
        String[] origin = locationCoordinateDTO.getOrigin();
        String[] destination = locationCoordinateDTO.getDestination();
        Coordinate coordinate = new Coordinate(origin[0],origin[1],destination[0],destination[1]);
        return orderApplication.createOrder(coordinate);
    }


    @PatchMapping("{id}")
    @RequestAdivce
    public OrderDTO takenOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) throws Exception {
        //lock
        Order order = new Order();
        order.setId(id);
        order.setStatus(orderDTO.getStatus());
        orderApplication.takenOrder(order);
        orderDTO.setStatus("Success");
        return orderDTO;
    }

    @GetMapping()
    @RequestAdivce
    public List<Order> getOrders(@RequestParam Integer page, @RequestParam Integer limit) throws Exception {
        return orderService.find(page,limit);
    }

}
