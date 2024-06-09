package com.example.enjoytrip.account.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//mock어쩌고 있으면 없어도 됨
@AutoConfigureMockMvc
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @MockBean
    AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("회원가입 요청을 받는 테스트 - mbti 있음")
    @Test
    void withMbtiAccountInsertTest() throws Exception {
        // given
        String email = "test@email.com";
        String password = "pass";
        String nickname = "daeho";
        AccountDto requestDto = new AccountDto();
        requestDto.setAccountEmail(email);
        requestDto.setAccountPassword(password);
        requestDto.setAccountNickname(nickname);

        // Convert the AccountDto to JSON
        String requestBody = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/accounts/{accountMbti}", "ENFJ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @DisplayName("회원가입 요청을 받는 테스트 - mbti 없음")
    @Test
    void withoutMbtiAccountInsertTest() throws Exception {
        // given
        String email = "test@email.com";
        String password = "pass";
        String nickname = "daeho";
        AccountDto requestDto = new AccountDto();
        requestDto.setAccountEmail(email);
        requestDto.setAccountPassword(password);
        requestDto.setAccountNickname(nickname);

        // Convert the AccountDto to JSON
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // Mock the service call
        given(accountService.accountInsert(any(AccountDto.class))).willReturn(1);


        //when,then
        mockMvc.perform(post("/accounts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
