package com.example.enjoytrip.board.service;

import com.example.enjoytrip.board.dto.BoardDto;
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

    Integer boardRecommendInsert(BoardDto boardDto);
    Integer boardRecommendDelete(BoardDto boardDto);
    List<Integer> boardRecommendList(Integer AccountId);
    int boardRecommendCount(Integer BoardId);
}
