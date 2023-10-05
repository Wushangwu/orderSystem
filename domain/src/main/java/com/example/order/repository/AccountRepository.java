package com.example.order.repository;

import com.example.order.domain.entity.Account;
import com.example.order.types.AccountId;
import com.example.order.types.AccountNumber;
import com.example.order.types.UserId;

public interface AccountRepository {
    Account find(AccountId id) throws Exception;
    Account find(AccountNumber accountNumber) throws Exception;
    Account find(UserId userId) throws Exception;
    Account save(Account account) throws Exception;
}
