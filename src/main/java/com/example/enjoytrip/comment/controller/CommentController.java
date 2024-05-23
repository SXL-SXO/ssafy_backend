package com.example.enjoytrip.comment.controller;

import com.example.enjoytrip.comment.dto.CommentDto;
import com.example.enjoytrip.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public List<CommentDto> commentList(@PathVariable("boardId") int commentBoardId){
        return commentService.commentList(commentBoardId);
    }

    @GetMapping("/boards/{commentId}")
    public ResponseEntity<CommentDto> commentDetail(@PathVariable("commentId") int commentId){
        try {
            CommentDto dto = commentService.commentDetail(commentId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("Error occurred while fetching comment detail", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<Integer> commentInsert(@RequestBody CommentDto commentDto){
        try {
            Integer result = commentService.commentInsert(commentDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while inserting comment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Integer> commentUpdate(@PathVariable("commentId") Integer commentId, @RequestBody CommentDto commentDto){
        try {
            commentDto.setCommentId(commentId);
            Integer result = commentService.commentUpdate(commentDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while updating comment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Integer> commentDelete(@PathVariable("commentId") Integer commentId){
        try {
            Integer result = commentService.commentDelete(commentId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while deleting comment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
