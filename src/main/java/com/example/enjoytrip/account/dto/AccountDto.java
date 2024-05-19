package com.example.enjoytrip.account.dto;

import com.example.enjoytrip.board.dto.BoardDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto {
    private Integer accountId;
    private String accountEmail;
    private String accountPassword;
    private String accountNickname;

    private AccountRole accountRole;
    private AccountMbti accountMbti;

    private List<BoardDto> accountBoard;
    private List<BoardDto> accountComment;
    private List<BoardDto> accountRecommendBoard;
    private List<BoardDto> accountRecommendSpot;
}
