package com.example.enjoytrip.comment.dao;

import com.example.enjoytrip.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    List<CommentDto> commentList(Integer commentBoardId);
    CommentDto commentDetail(Integer commentId);
    Integer commentInsert(CommentDto commentDto);
    Integer commentUpdate(CommentDto commentDto);
    Integer commentDelete(Integer commentId);
}
