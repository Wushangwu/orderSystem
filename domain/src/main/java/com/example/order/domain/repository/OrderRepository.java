package com.example.order.domain.repository;

import com.example.order.domain.entity.Account;
import com.example.order.domain.entity.Order;
import com.example.order.types.AccountId;
import com.example.order.types.AccountNumber;
import com.example.order.types.UserId;

import java.util.List;

public interface OrderRepository {
    void save(Order order) throws Exception;
    void taken(Order order) throws Exception;
    List<Order> find(UserId userId);
}
