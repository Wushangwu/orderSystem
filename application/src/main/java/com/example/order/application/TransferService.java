package com.example.order.application;

import com.example.order.common.Result;
import com.example.order.exception.DailyLimitExceededException;

import java.math.BigDecimal;

public interface TransferService {

    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) throws Exception, DailyLimitExceededException;

}