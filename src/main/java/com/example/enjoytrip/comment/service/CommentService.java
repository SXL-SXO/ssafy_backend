package com.example.enjoytrip.comment.service;

import com.example.enjoytrip.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> commentList(Integer commentBoardId);
    CommentDto commentDetail(Integer commentId);
    Integer commentInsert(CommentDto commentDto);
    Integer commentUpdate(CommentDto commentDto);
    Integer commentDelete(Integer commentId);
}
