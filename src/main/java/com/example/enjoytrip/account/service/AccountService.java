package com.example.enjoytrip.account.service;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.board.dto.BoardDto;

import java.util.List;

public interface AccountService {
    Integer accountInsert(AccountDto accountDto);

    Integer accountUpdate(AccountDto accountDto);

    Integer accountDelete(Integer accountId);

    AccountDto findById(Integer accountId);

    Integer findByEmail(String accountEmail);

    Integer login(String accountEmail, String accountPassword);

    List<BoardDto> accountBoard(Integer accountId);
    List<BoardDto> accountComment(Integer accountId);
    List<BoardDto> accountRecommendBoard(Integer accountId);
//    List<BoardDto> accountRecommendSpot(Integer accountId);
}
