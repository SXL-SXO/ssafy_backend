package com.example.enjoytrip.comment.controller;

import com.example.enjoytrip.comment.dto.CommentDto;
import com.example.enjoytrip.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController{

    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public List<CommentDto> commentList(@PathVariable("boardId") int commentBoardId){
        List<CommentDto> list = commentService.commentList(commentBoardId);
        return list;
    }

    @GetMapping("/boards/{commentId}")
    public ResponseEntity<CommentDto> commentDetail(@PathVariable("commentId") int commentId){
        CommentDto dto = commentService.commentDetail(commentId);
        return ResponseEntity.internalServerError().body(dto);
    }

    @PostMapping()
    public Integer commentInsert(@RequestBody CommentDto commentDto){
        System.out.println(commentDto);
        return commentService.commentInsert(commentDto);
    }

    @PutMapping("/{commentId}")
    public Integer commentUpdate(@PathVariable("commentId") Integer commentId, @RequestBody CommentDto commentDto){
        commentDto.setCommentId(commentId);
        System.out.println(commentDto);
        return commentService.commentUpdate(commentDto);
    }

    @DeleteMapping("/{commentId}")
    public Integer commentDelete(@PathVariable("commentId") Integer commentId){
        System.out.println("확인-delete-comment"+commentId);
        return commentService.commentDelete(commentId);
    }


}
