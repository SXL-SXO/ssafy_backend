package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.account.service.AccountService;
import com.example.enjoytrip.exception.CustomException;
import com.example.enjoytrip.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 회원정보관련
@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final AccountService accountService;
    @GetMapping("/login")
    public ResponseEntity<AccountDto> login(@RequestParam(name = "accountEmail") String accountEmail, @RequestParam(name = "accountPassword") String accountPassword) {
        log.info("accountEmail = {}", accountEmail);
        log.info("accountPassword = {}", accountPassword);
        AccountDto result = accountService.login(accountEmail, accountPassword);
        if(result.getAccountMbti() == null) {result.setAccountMbti(AccountMbti.NULL);}
        return ResponseEntity.ok().body(result);
    }
}
