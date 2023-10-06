package com.example.order.persistence;

import com.example.order.domain.entity.Account;
import com.example.order.persistence.DO.AccountDO;

public interface AccountBuilder {
    Account toAccount(AccountDO accountDO) throws Exception;

    AccountDO fromAccount(Account account);
}
