package com.example.order.web.Controller;

import com.example.order.domain.entity.LocationCoordinate;
import com.example.order.domain.entity.Order;
import com.example.order.domain.service.LocationCoordinateService;
import com.example.order.domain.service.OrderService;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.web.DTO.LocationCoordinateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    public LocationCoordinateService locationCoordinateService;

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public Order createOrder(@RequestBody LocationCoordinateDTO locationCoordinateDTO) throws Exception {
        if(locationCoordinateDTO.getOrigin().length != 2 && locationCoordinateDTO.getDestination().length != 2){
            throw new DisCorrectInputException("input error from user,please check it");
        }
        String[] origin = locationCoordinateDTO.getOrigin();
        String[] destination = locationCoordinateDTO.getDestination();
        LocationCoordinate locationCoordinate = new LocationCoordinate(origin[0],origin[1],destination[0],destination[1]);
        Order order = new Order();
        order.setStatus("UNASSIGNED");
        order.setDistance(String.valueOf(locationCoordinateService.calculateDistance(locationCoordinate)));
        orderService.create(order);
        return new Order();
    }

    @RequestMapping(value = "/orders/{id}",method = RequestMethod.PATCH)
    public Order takenOrder(@PathVariable("id") String id) throws Exception {
        orderService.take(id);
        Order order = new Order();
        order.setStatus("Success");
        return order;
    }

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public List<Order> getOrders(@RequestParam Integer page, @RequestParam Integer limit) throws Exception {
        return orderService.find(page,limit);
    }

}
