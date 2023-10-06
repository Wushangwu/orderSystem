package com.example.order.domain.external;

import com.example.order.types.Currency;
import com.example.order.types.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);

}

