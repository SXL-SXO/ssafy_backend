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

    Integer boardRecommend(Integer boardId, int boardRecommend);
    List<BoardDto> boardRecommendList(Integer AccountId);
}
