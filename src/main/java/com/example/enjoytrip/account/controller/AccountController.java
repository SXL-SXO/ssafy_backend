package com.example.enjoytrip.account.controller;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.account.service.AccountService;
import com.example.enjoytrip.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping({"/", "/{accountMbti}"})
    public ResponseEntity<Integer> accountInsert(@PathVariable(name = "accountMbti", required = false) AccountMbti accountMbti, @RequestBody AccountDto accountDto){
        if(accountMbti != null) {
            accountDto.setAccountMbti(accountMbti);
        }
        Integer result = accountService.accountInsert(accountDto);
        if(result <= 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> findById(@PathVariable("accountId") Integer accountId){
        AccountDto accountDto = accountService.findById(accountId);
        if(accountDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping("/check/{accountEmail}")
    public ResponseEntity<Void> findByEmail(@PathVariable("accountEmail") String accountEmail){
        Integer result = accountService.findByEmail(accountEmail);
        if (result != null && result > 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Integer> accountUpdate(@PathVariable("accountId") Integer accountId, @RequestBody AccountDto accountDto){
        Integer result = accountService.accountUpdate(accountDto);
        if(result <= 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Integer> accountDelete(@PathVariable("accountId") Integer accountId){
        Integer result = accountService.accountDelete(accountId);
        if(result <= 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/board/{accountId}")
    public ResponseEntity<List<BoardDto>> accountBoard(@PathVariable("accountId") Integer accountId){
        List<BoardDto> boardList = accountService.accountBoard(accountId);
        if(boardList == null || boardList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @GetMapping("/comment/{accountId}")
    public ResponseEntity<List<BoardDto>> accountComment(@PathVariable("accountId") Integer accountId){
        List<BoardDto> commentList = accountService.accountComment(accountId);
        if(commentList == null || commentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/recommendboard/{accountId}")
    public ResponseEntity<List<BoardDto>> accountRecommendBoard(@PathVariable("accountId") Integer accountId){
        List<BoardDto> recommendBoardList = accountService.accountRecommendBoard(accountId);
        if(recommendBoardList == null || recommendBoardList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recommendBoardList, HttpStatus.OK);
    }
}
