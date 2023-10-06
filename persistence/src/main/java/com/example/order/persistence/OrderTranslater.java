package com.example.order.persistence;

import com.example.order.domain.entity.Account;
import com.example.order.domain.entity.Order;
import com.example.order.persistence.DO.AccountDO;
import com.example.order.persistence.DO.OrderDO;

public interface OrderTranslater {

    Order toOrder(OrderDO orderDO) throws Exception;

    OrderDO fromOrder(Order order);
}
