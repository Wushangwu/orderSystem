package com.example.order.persistence;

import com.example.order.entity.Order;
import com.example.order.persistence.DO.OrderDO;

public interface OrderTranslater {

    Order toOrder(OrderDO orderDO) throws Exception;

    OrderDO fromOrder(Order order);
}
