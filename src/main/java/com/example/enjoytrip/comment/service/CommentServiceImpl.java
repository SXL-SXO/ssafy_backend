package com.example.enjoytrip.comment.service;

import com.example.enjoytrip.comment.dao.CommentDao;
import com.example.enjoytrip.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentDao commentDao;

    @Override
    public List<CommentDto> commentList(Integer commentBoardId) {
        return commentDao.commentList(commentBoardId);
    }

    @Override
    public CommentDto commentDetail(Integer commentId) {
        return commentDao.commentDetail(commentId);
    }

    @Override
    public Integer commentInsert(CommentDto commentDto) {
        return commentDao.commentInsert(commentDto);
    }

    @Override
    public Integer commentUpdate(CommentDto commentDto) {
        return commentDao.commentUpdate(commentDto);
    }

    @Override
    public Integer commentDelete(Integer commentId) {
        return commentDao.commentDelete(commentId);
    }
}
