package com.example.enjoytrip.board.dto;

import com.example.enjoytrip.comment.domain.CommentDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto {
    private int boardId;
    private int boardTouristspotId;
    private String boardTitle;
    private String boardContent;
    private String boardAccountNickname;
    private int boardAccountId;
    private int boardRecommend;
    private List<CommentDto> boardComment;
}
