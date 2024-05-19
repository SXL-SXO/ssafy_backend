package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 회원정보관련
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AccountService accountService;

    @GetMapping("/login")
    public Integer login(@RequestParam AccountDto accountDto){
        return accountService.login(accountDto.getAccountEmail(), accountDto.getAccountPassword());
    }
}
