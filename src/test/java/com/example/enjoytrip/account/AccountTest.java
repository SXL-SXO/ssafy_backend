package com.example.enjoytrip.account;

import com.example.enjoytrip.account.dao.AccountDao;
import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.example.enjoytrip.account.dto.AccountRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class AccountTest {
    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountService accountService;

    AccountDto accountDto = null;

    @Test
    @DisplayName("의존성 주입 테스트")
    void testDi() {
        assertNotNull(accountDao);
        assertNotNull(accountService);
    }
    @BeforeEach
    void beforeEach() {
        accountDto = new AccountDto();
        accountDto.setAccountEmail("ssafy@ssafy.com");
        accountDto.setAccountPassword("1234");
        accountDto.setAccountRole(USER);
        accountDto.setAccountNickname("김싸피");

    }

    @Test
    @Transactional
    @DisplayName("회원가입")
    void checkAccountInsert (){
        //give

        //when
        Integer result = accountService.accountInsert(accountDto);

        //then
        assertEquals(result,1);
    }
    @Test
    @Transactional
    @DisplayName("회원가입전 - 존재하는 이메일 체크 (존재X) ")
    void beforeInsertCheckEmailNotExist(){
        //when
        Integer result = accountService.findByEmail("ssafy@ssafy.com");

        //then
        assertEquals(result,1);
    }
    @Test
    @Transactional
    @DisplayName("회원가입전 - 존재하는 이메일 체크 (존재O) ")
    void BeforeInsertCheckEmailExist(){
        //give
        accountService.accountInsert(accountDto);

        //when
        Integer result = accountService.findByEmail("ssafy@ssafy.com");

        //then
        assertEquals(result,null);
    }
    @Test
    @Transactional
    @DisplayName("회원정보확인 - 회원(존재O)")
    void AfterInsertCheckEmailExist(){
        //give
        accountService.accountInsert(accountDto);

        //when
        AccountDto result = accountService.findById(accountDto.getAccountId());

        //then
        assertEquals(result.getAccountEmail(),"ssafy@ssafy.com");
        assertEquals(result.getAccountPassword(),"1234");
        assertEquals(result.getAccountRole(),USER);
        assertEquals(result.getAccountNickname(),"김싸피");
    }

    @Test
    @Transactional
    @DisplayName("회원정보확인 - 회원(존재X)")
    void AfterInsertCheckEmailNotExist(){
        //give

        //when
        AccountDto result = accountService.findById(0);

        //then
        assertEquals(result,null);
    }
}
