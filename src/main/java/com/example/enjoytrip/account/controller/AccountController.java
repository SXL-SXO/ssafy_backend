package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 회원정보관련
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping({"/", "/{accountMbti}"})
    public Integer accountInsert(@PathVariable(name = "accountMbti", required = false) AccountMbti accountMbti, @RequestBody AccountDto accountDto){
        if(accountMbti!=null) { accountDto.setAccountMbti(accountMbti);
        }
        return accountService.accountInsert(accountDto);
    }
    @GetMapping("/{accountId}")
    public AccountDto findById(@PathVariable("accountId") Integer accountId){
        return accountService.findById(accountId);
    }
    @GetMapping("/check/{accountEmail}")
    public Integer findByEmail(@PathVariable("accountEmail") String accountEmail){
        return accountService.findByEmail(accountEmail);
    }
    @PutMapping("/{accountId}")
    public Integer accountUpdate(@PathVariable("accountId") Integer accountId, @RequestBody AccountDto accountDto){
        return accountService.accountUpdate(accountDto);
    }
    @DeleteMapping("/{accountId}")
    public Integer accountDelete(@PathVariable("accountId") Integer accountId){
        return accountService.accountDelete(accountId);
    }

}
