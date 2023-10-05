package com.example.order.domain.service;

import com.example.order.domain.entity.Account;
import com.example.order.exception.DailyLimitExceededException;
import com.example.order.types.ExchangeRate;
import com.example.order.types.Money;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws Exception, DailyLimitExceededException;
}
