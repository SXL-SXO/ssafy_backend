package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

// 회원정보관련
@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final AccountService accountService;
    @GetMapping("/login")
    public AccountDto login(@RequestParam String accountEmail, @RequestParam String accountPassword) {
        return accountService.login(accountEmail, accountPassword);
    }
}
