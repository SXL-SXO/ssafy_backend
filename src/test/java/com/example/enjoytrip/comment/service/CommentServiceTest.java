package com.example.enjoytrip.comment.service;

import com.example.enjoytrip.board.dao.BoardDao;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.board.service.BoardService;
import com.example.enjoytrip.comment.dao.CommentDao;
import com.example.enjoytrip.comment.dto.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


//@AutoConfigureMockMvc
@SpringBootTest
class CommentServiceTest {

    @Autowired
    BoardDao boardDao;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentDao commentDao;

    @Autowired
    CommentService commentService;


    BoardDto boardDto = null;
    CommentDto commentDto = null;


    @Test
    @DisplayName("의존성 주입 테스트")
    void testDi() {
        assertNotNull(commentDao);
        assertNotNull(commentService);
    }

    @BeforeEach
    void beforeEach() {
        boardDto = new BoardDto();
        boardDto.setTouristspotId(1);
        boardDto.setBoardTitle("배고파");
        boardDto.setBoardContent("안된다");
        boardDto.setAccountId(1);

//        BoardDto.builder()
//                .boardTouristspotId(1)
//                .boardTitle("배고파")
//                .


        commentDto = new CommentDto();
        commentDto.setCommentContent("위 게시글에 대한 댓글작성");
        commentDto.setAccountId(1);


    }

    @Test
    @Transactional
    @DisplayName("comment 등록 테스트 -- 올바른 게시글에 저장")
    void testcommentInsert() {
        //given
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());

        //when, then
        assertEquals(commentService.commentInsert(commentDto), 1);
        assertEquals(commentService.commentList(boardDto.getBoardId()).get(0).getCommentId(), commentDto.getCommentId());
    }

    @Test
    @Transactional
    @DisplayName("comment 등록 테스트 - nickname db에 저장하기")
    void testcommentInsertNickname() {
        //given
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());

        //when
        commentService.commentInsert(commentDto);

        //then
        assertEquals(commentDto.getAccountNickname(), "감자");
    }

    @Test
    @DisplayName("comment 수정 테스트 -- 특정 댓글 수정하기")
    @Transactional
    void testcommentUpdate() {
        //given
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());
        commentService.commentInsert(commentDto);

        //when
        commentDto.setCommentContent("위 게시글에 대한 댓글수정");

        //then
        assertEquals(commentService.commentUpdate(commentDto), 1);
//        assertEquals();
    }

    @Test
    @DisplayName("comment 삭제 테스트")
    @Transactional
    void testcommentDelete() {
        //given
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());
        commentService.commentInsert(commentDto);

        // when, then
        assertEquals(commentService.commentDelete(commentDto.getCommentId()), 1);
    }

    @Test
    @DisplayName("comment 조회 테스트")
    @Transactional
    void testcommentList() {
        //given
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());

        //when
        commentService.commentInsert(commentDto);
        commentService.commentInsert(commentDto);
        commentService.commentInsert(commentDto);
        commentService.commentInsert(commentDto);

        //then
        assertEquals(commentService.commentList(boardDto.getBoardId()).size(), 4);
    }

    @Test
    @DisplayName("comment 디테일 가져오는지 테스트")
    @Transactional
    void testboardwithcomment(){
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());
        commentService.commentInsert(commentDto);
        commentService.commentInsert(commentDto);
        commentService.commentInsert(commentDto);
        commentService.commentInsert(commentDto);

        boardDto = boardService.BoardDetailswithComment(boardDto.getBoardId());
        System.out.println(boardDto.toString());
        assertEquals(boardDto.getBoardComment().size(), 4);

    }


}
