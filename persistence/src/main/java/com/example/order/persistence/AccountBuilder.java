package com.example.order.persistence;

import com.example.order.domain.entity.Account;

public interface AccountBuilder {
    Account toAccount(AccountDO accountDO) throws Exception;

    AccountDO fromAccount(Account account);
}
