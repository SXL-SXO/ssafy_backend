package com.example.enjoytrip.board.controller;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.board.service.BoardService;
import com.example.enjoytrip.common.dto.PageDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@AllArgsConstructor
public class BoardController {

    BoardService boardService;

    @GetMapping("/{pageSize}/{pageNum}")
    public List<BoardDto> boardList(@PathVariable("pageSize") int pageSize, @PathVariable("pageNum") int pageNum){
        PageDto pageDto = new PageDto(pageSize, pageNum);
        List<BoardDto> list = boardService.boardList(pageDto);
        return list;
    }

    @GetMapping("/{boardId}")
    public BoardDto boardDetail(@PathVariable("boardId") int boardId){
        BoardDto dto = boardService.boardDetail(boardId);
        return dto;
    }

    @PutMapping("/{boardId}")
    public Integer boardUpdate(@PathVariable("boardId") int boardId, BoardDto dto){
        return boardService.boardUpdate(dto);
    }

    @PutMapping("/recommend/{boardId}")
    public Integer boardRecommend(@PathVariable("boardId") int boardId, int boardRecommend){
        return boardService.boardRecommend(boardId, boardRecommend);
    }
    @PostMapping
    public Integer boardInsert(BoardDto dto){
        return boardService.boardInsert(dto);
    }

    @DeleteMapping("/{boardId}")
    public Integer boardDelete(@PathVariable("boardId") int boardId){
        return boardService.boardDelete(boardId);
    }
}
