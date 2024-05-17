package com.example.enjoytrip.account.service;

import com.example.enjoytrip.account.dto.AccountDto;

public interface AccountService {
    Integer accountInsert(AccountDto accountDto);

    int accountUpdate(AccountDto accountDto);

    int accountDelete(Integer accountId);

    AccountDto findById(Integer accountId);

    Integer findByEmail(String accountEmail);

    Integer login(String accountEmail, String accountPassword);
}
