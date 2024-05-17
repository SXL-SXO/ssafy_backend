package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 회원정보관련
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AccountService accountService;

    @GetMapping("/login")
    public Integer login(@RequestBody AccountDto accountDto){
        return accountService.login(accountDto.getAccountEmail(), accountDto.getAccountPassword());
    }
}
