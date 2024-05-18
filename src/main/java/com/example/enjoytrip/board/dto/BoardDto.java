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
    private Integer boardId;
    private Integer touristspotId;
    private String boardTitle;
    private String boardContent;
    private String accountNickname;
    private Integer accountId;
    private List<CommentDto> boardComment;
}
