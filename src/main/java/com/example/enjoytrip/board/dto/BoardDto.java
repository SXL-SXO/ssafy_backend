package com.example.enjoytrip.board.dto;

import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.comment.dto.CommentDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto {
    private Integer boardId;
    private String boardTitle;
    private String boardContent;
    private BoardCategory boardCategory;
    private Integer boardRecommendCount;
    private List<CommentDto> boardComment;
    private Integer touristspotId;
    private String touristspotTitle;
    private String accountNickname;
    private Integer accountId;
    private AccountMbti accountMbti;
}
