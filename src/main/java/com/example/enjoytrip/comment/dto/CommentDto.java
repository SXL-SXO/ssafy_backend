package com.example.enjoytrip.comment.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {
    private int commentId;
    private String commentContent;

    private int boardId;
    private int accountId;
    private String accountNickname;
}
