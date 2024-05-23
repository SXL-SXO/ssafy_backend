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
import org.springframework.http.HttpStatus;
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

    @GetMapping()
    public ResponseEntity<List<BoardDto>> boardList(
            @RequestParam (required = false) Integer pageSize,
            @RequestParam (required = false) Integer pageNum,
            @RequestParam (required = false) String searchWord,
            @RequestParam (required = false) AccountMbti searchMbti
    ){
        try {
            PageDto pageDto = new PageDto(pageSize, pageNum, searchWord, searchMbti);
            List<BoardDto> list = boardService.boardList(pageDto);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error occurred while fetching board list", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> boardDetail(@PathVariable("boardId") int boardId){
        try {
            BoardDto dto = boardService.boardDetail(boardId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("Error occurred while fetching board detail", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Integer> boardUpdate(@PathVariable("boardId") Integer boardId, @RequestBody BoardDto boardDto){
        try {
            boardDto.setBoardId(boardId);
            return ResponseEntity.ok(boardService.boardUpdate(boardDto));
        } catch (Exception e) {
            log.error("Error occurred while updating board", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> boardInsert(@RequestBody BoardDto boardDto){
        try {
            return ResponseEntity.ok(boardService.boardInsert(boardDto));
        } catch (Exception e) {
            log.error("Error occurred while inserting board", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Integer> boardDelete(@PathVariable("boardId") Integer boardId){
        try {
            return ResponseEntity.ok(boardService.boardDelete(boardId));
        } catch (Exception e) {
            log.error("Error occurred while deleting board", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/recommends")
    public ResponseEntity<Integer> boardRecommendInsert(@RequestBody Map<String, Integer> recomend){
        try {
            Integer result = boardService.boardRecommendInsert(recomend.get("boardId"), recomend.get("accountId"));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while inserting board recommendation", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/recommends")
    public ResponseEntity<Integer> boardRecommendDelete(@RequestBody Map<String, Integer> recomend){
        try {
            Integer result = boardService.boardRecommendDelete(recomend.get("boardId"), recomend.get("accountId"));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while deleting board recommendation", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/recommends/account")
    public ResponseEntity<List<Integer>> boardRecommendList(@RequestBody Integer accountId){
        try {
            List<Integer> result = boardService.boardRecommendList(accountId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while fetching board recommendation list by account ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/recommends/board")
    public ResponseEntity<Integer> boardRecommendCount(@RequestBody Integer boardId){
        try {
            Integer result = boardService.boardRecommendCount(boardId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while counting board recommendations", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
        try {
            // 이미지 업로드 처리 코드

            return ResponseEntity.ok("이미지 업로드 성공");
        } catch (Exception e) {
            log.error("Error occurred while uploading image", e);
            return ResponseEntity.badRequest().body("이미지 업로드 실패");
        }
    }

}
