package com.example.enjoytrip.comment.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {
    private Integer commentId;
    private String commentContent;

    private Integer boardId;
    private Integer accountId;
    private String accountNickname;
}
