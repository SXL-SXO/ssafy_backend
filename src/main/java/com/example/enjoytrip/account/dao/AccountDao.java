package com.example.enjoytrip.account.dao;

import com.example.enjoytrip.account.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao {
    int accountInsert(AccountDto accountDto);
    int accountUpdate(AccountDto accountDto);
    int accountDelete(Integer accountId);

    AccountDto findById (Integer accountId);
    AccountDto findByEmail (String accountEmail);

    Integer login (String accountEmail, String accountPassword);
}
