package com.example.enjoytrip.account.dto;

import lombok.*;

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
}
