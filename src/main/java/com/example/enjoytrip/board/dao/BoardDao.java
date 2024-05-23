package com.example.enjoytrip.board.dao;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    List<BoardDto> boardList(PageDto pageDto);
    BoardDto boardDetail(Integer boardId);
    Integer boardInsert(BoardDto boardDto);
    Integer boardUpdate(BoardDto boardDto);
    Integer boardDelete(Integer boardId);

    Integer boardRecommendInsert(BoardDto boardDto);
    Integer boardRecommendDelete(BoardDto boardDto);
    List<Integer> boardRecommendList(Integer accountId);
    int boardRecommendCount(Integer boardId);
}
