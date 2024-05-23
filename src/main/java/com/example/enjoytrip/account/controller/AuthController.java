package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final AccountService accountService;

    @GetMapping("/login")
    public ResponseEntity<AccountDto> login(@RequestParam(name = "accountEmail") String accountEmail, @RequestParam(name = "accountPassword") String accountPassword) {
        log.info("accountEmail = {}", accountEmail);
        log.info("accountPassword = {}", accountPassword);

        try {
            AccountDto result = accountService.login(accountEmail, accountPassword);
            if (result.getAccountMbti() == null) {
                result.setAccountMbti(AccountMbti.NULL);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Login failed", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // 로그인 실패 시 401 상태 반환
        }
    }
}
