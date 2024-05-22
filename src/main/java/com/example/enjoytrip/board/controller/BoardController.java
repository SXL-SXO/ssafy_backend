package com.example.enjoytrip.board.controller;

import static ch.qos.logback.core.joran.JoranConstants.NULL;

import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.board.service.BoardService;
import com.example.enjoytrip.common.dto.PageDto;
import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final WebApplicationContext context;

    @GetMapping()
    public List<BoardDto> boardList(
            @RequestParam (required = false, name = "pageSize") Integer pageSize,
            @RequestParam (required = false, name = "pageNum") Integer pageNum,
            @RequestParam (required = false, name = "searchWord") String searchWord,
            @RequestParam (required = false, name = "searchMbti") AccountMbti searchMbti){
//        log.info("pageSize = {}", pageSize);
//        log.info("pageNum = {}", pageNum);
//        log.info("searchWord = {}", searchWord);
//        log.info("searchMbti = {}", searchMbti);
//        searchWord ==
        PageDto pageDto = new PageDto(pageSize, pageNum, searchWord, searchMbti);
//        System.out.println(pageDto);
        List<BoardDto> list = boardService.boardList(pageDto);
        System.out.println(list);
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
    @PostMapping
    public Integer boardInsert(BoardDto dto){
        return boardService.boardInsert(dto);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Integer> boardDelete(@PathVariable("boardId") Integer boardId){
        System.out.println(boardId);
        return ResponseEntity.ok().body(boardService.boardDelete(boardId));
    }


    @PostMapping("/recommends")
    public Integer boardRecommendInsert(@RequestBody Map<String, Integer> recomend){
        return boardService.boardRecommendInsert(recomend.get("boardId"), recomend.get("accountId"));
    }

    @DeleteMapping("/recommends")
    public Integer boardRecommendDelete(@RequestBody Map<String, Integer> recomend){
        return boardService.boardRecommendDelete(recomend.get("boardId"), recomend.get("accountId"));
    }

    @GetMapping("/recommends/account")
    public List<Integer> boardRecommendList(@RequestBody Integer accountId){
        return boardService.boardRecommendList(accountId);
    }
    @GetMapping("/recommends/board")
    public int boardRecommendCount(@RequestBody Integer boardId){
        return boardService.boardRecommendCount(boardId);
    }
}
