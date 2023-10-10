package com.example.order.web.Controller;

import com.example.order.config.RedisUtil;
import com.example.order.constant.OrderStatus;
import com.example.order.entity.Coordinate;
import com.example.order.entity.Order;
import com.example.order.service.LocationCoordinateService;
import com.example.order.service.OrderService;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.web.DTO.LocationCoordinateDTO;
import com.example.order.web.DTO.OrderDTO;
import com.example.order.web.Exception.RedisLockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public LocationCoordinateService locationCoordinateService;

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Order createOrder(@RequestBody LocationCoordinateDTO locationCoordinateDTO) throws Exception {
        if(locationCoordinateDTO.getOrigin().length != 2 && locationCoordinateDTO.getDestination().length != 2){
            throw new DisCorrectInputException("input error from user,please check it");
        }
        String[] origin = locationCoordinateDTO.getOrigin();
        String[] destination = locationCoordinateDTO.getDestination();
        Coordinate coordinate = new Coordinate(origin[0],origin[1],destination[0],destination[1]);
        Order order = new Order();
        order.setStatus(OrderStatus.UNASSIGNED);
        order.setDistance(String.valueOf(locationCoordinateService.calculateDistance(coordinate)));
        orderService.create(order);
        return order;
    }

    @RequestMapping(value = "/orders",method = RequestMethod.PATCH)
    public Order takenOrder(@RequestParam String id, @RequestBody OrderDTO orderDTO) throws Exception {
        if(redisUtil.setnx("order_taken_" + id,"success", 60L)){
            Order order = new Order();
            order.setStatus(orderDTO.getStatus());
            order.setId(id);
            orderService.take(order);
            order.setStatus(OrderStatus.SUCCESS);
            return order;
        }else{
            throw new RedisLockedException("It is already taken");
        }
    }

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public List<Order> getOrders(@RequestParam Integer page, @RequestParam Integer limit) throws Exception {
        return orderService.find(page,limit);
    }

}
