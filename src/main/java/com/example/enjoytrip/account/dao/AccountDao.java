package com.example.enjoytrip.account.dao;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
    Integer accountInsert(AccountDto accountDto);
    Integer accountUpdate(AccountDto accountDto);
    Integer accountDelete(Integer accountId);

    AccountDto findById (Integer accountId);
    AccountDto findByEmail (String accountEmail);

    AccountDto login (AccountDto accountDto);

    List<BoardDto> accountBoard(Integer accountId);
    List<BoardDto> accountComment(Integer accountId);
    List<BoardDto> accountRecommendBoard(Integer accountId);
//    List<BoardDto> accountRecommendSpot(Integer accountId);
}
