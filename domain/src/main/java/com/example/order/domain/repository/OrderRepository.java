package com.example.order.domain.repository;

import com.example.order.domain.entity.Account;
import com.example.order.domain.entity.Order;
import com.example.order.types.AccountId;
import com.example.order.types.AccountNumber;
import com.example.order.types.UserId;

import java.util.List;

public interface OrderRepository {
    int save(Order order) throws Exception;
    int taken(Order order) throws Exception;
    List<Order> find(UserId userId) throws Exception;
}
