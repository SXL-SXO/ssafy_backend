package com.example.enjoytrip.common.dto;

import com.example.enjoytrip.account.dto.AccountMbti;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@ToString
public class PageDto {
    private int pageSize;
    private int pageNum;
    private String searchWord;
}
