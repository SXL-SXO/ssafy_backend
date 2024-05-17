package com.example.enjoytrip.board.service;

import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.comment.domain.CommentDto;
import com.example.enjoytrip.common.dto.PageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {
    List<BoardDto> boardList(PageDto pageDto);

    @Transactional
    BoardDto BoardDetailswithComment(Integer boardId);

    BoardDto boardDetail(Integer boardId);
    Integer boardInsert(BoardDto boardDto);
    Integer boardUpdate(BoardDto boardDto);
    Integer boardDelete(Integer boardId);

    Integer boardRecommend(Integer boardId, Integer AccountId, int boardRecommend);
    List<BoardDto> boardRecommendList(Integer AccountId);

//    List<CommentDto> commentList(Integer boardId);
}
