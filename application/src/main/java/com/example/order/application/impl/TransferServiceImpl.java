package com.example.order.application.impl;

import com.example.order.application.TransferService;
import com.example.order.common.Result;
import com.example.order.domain.entity.Account;
import com.example.order.domain.service.AccountTransferService;
import com.example.order.domain.types.AuditMessage;
import com.example.order.exception.DailyLimitExceededException;
import com.example.order.domain.external.ExchangeRateService;
import com.example.order.messaging.AuditMessageProducer;
import com.example.order.domain.repository.AccountRepository;
import com.example.order.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    private AuditMessageProducer auditMessageProducer;
    private ExchangeRateService exchangeRateService;
    private AccountTransferService accountTransferService;

//    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) throws Exception, DailyLimitExceededException {
        Money targetMoney = new Money(targetAmount, new Currency(targetCurrency));

        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));

        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));

        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        accountRepository.save(sourceAccount);

        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(message);

        return Result.success(true);
    }
}
