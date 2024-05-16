package com.example.enjoytrip.account.service;

import com.example.enjoytrip.account.dao.AccountDao;
import com.example.enjoytrip.account.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Override
    public Integer accountInsert(AccountDto accountDto) {
        return accountDao.accountInsert(accountDto);
    }

    @Override
    public int accountUpdate(AccountDto accountDto) {
        return accountDao.accountUpdate(accountDto);
    }

    @Override
    public int accountDelete(Integer accountId) {
        return accountDao.accountDelete(accountId);
    }

    @Override
    public AccountDto findById(Integer accountId) {
        return accountDao.findById(accountId);
    }

    @Override
    public Integer findByEmail(String accountEmail) {
        AccountDto accountDto = accountDao.findByEmail(accountEmail);

        if(accountDto == null) {
            return 1;
        }
        return null;
    }
}
