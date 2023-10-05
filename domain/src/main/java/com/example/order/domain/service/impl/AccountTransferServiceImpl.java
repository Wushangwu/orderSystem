package com.example.order.domain.service.impl;

import com.example.order.domain.entity.Account;
import com.example.order.domain.service.AccountTransferService;
import com.example.order.exception.DailyLimitExceededException;
import com.example.order.types.ExchangeRate;
import com.example.order.types.Money;
import org.springframework.stereotype.Service;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {
    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws Exception, DailyLimitExceededException {
        Money sourceMoney =  exchangeRate.exchageTo(targetMoney);
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);
    }
}
