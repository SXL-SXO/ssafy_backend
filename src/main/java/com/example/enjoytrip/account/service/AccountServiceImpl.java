package com.example.enjoytrip.account.service;

import com.example.enjoytrip.account.dao.AccountDao;
import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.exception.CustomException;
import com.example.enjoytrip.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Override
    public Integer accountInsert(AccountDto accountDto) {
        return accountDao.accountInsert(accountDto);
    }

    @Override
    public Integer accountUpdate(AccountDto accountDto) {
        return accountDao.accountUpdate(accountDto);
    }

    @Override
    public Integer accountDelete(Integer accountId) {
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

    @Override
    public AccountDto login(String accountEmail, String accountPassword) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountEmail(accountEmail);
        accountDto.setAccountPassword(accountPassword);
        AccountDto result = accountDao.login(accountDto);
        if(result == null) throw new CustomException(ErrorCode.LoginFail);
        return result;
    }

    @Override
    public List<BoardDto> accountBoard(Integer accountId) {
        return accountDao.accountBoard(accountId);
    }

    @Override
    public List<BoardDto> accountComment(Integer accountId) {
        return accountDao.accountComment(accountId);
    }

    @Override
    public List<BoardDto> accountRecommendBoard(Integer accountId) {
        return accountDao.accountRecommendBoard(accountId);
    }

//    @Override
//    public List<BoardDto> accountRecommendSpot(Integer accountId) {
//        return accountDao.accountRecommendSpot(accountId);
//    }
}
