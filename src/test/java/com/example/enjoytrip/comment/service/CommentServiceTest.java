package com.example.enjoytrip.comment.service;

import com.example.enjoytrip.account.dao.AccountDao;
import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import com.example.enjoytrip.board.dao.BoardDao;
import com.example.enjoytrip.board.dto.BoardCategory;
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

@Transactional
//@AutoConfigureMockMvc
@SpringBootTest
class CommentServiceTest {
    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountService accountService;

    @Autowired
    BoardDao boardDao;

    @Autowired
    BoardService boardService;

    @Autowired
    CommentDao commentDao;

    @Autowired
    CommentService commentService;

    AccountDto accountDto = null;
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
        accountDto = new AccountDto();
        accountDto.setAccountNickname("감자");
        accountDto.setAccountEmail("KKKK");
        accountDto.setAccountPassword("123456");
        accountService.accountInsert(accountDto);

        boardDto = new BoardDto();
        boardDto.setTouristspotId(1);
        boardDto.setBoardTitle("배고파");
        boardDto.setBoardContent("안된다");
        boardDto.setAccountId(accountDto.getAccountId());
        boardDto.setBoardCategory(BoardCategory.QUESTION);

//        BoardDto.builder()
//                .boardTouristspotId(1)
//                .boardTitle("배고파")
//                .


        commentDto = new CommentDto();
        commentDto.setCommentContent("위 게시글에 대한 댓글작성");
        commentDto.setAccountId(accountDto.getAccountId());
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
    @DisplayName("comment 등록 테스트 - nickname db에 저장되는지")
    void testcommentInsertNickname() {
        //given
        boardService.boardInsert(boardDto);
        commentDto.setBoardId(boardDto.getBoardId());

        //when
        commentService.commentInsert(commentDto);
        CommentDto result = commentService.commentDetail(commentDto.getCommentId());

        //then
        System.out.println(result.getAccountNickname());
//        assertEquals(result.getAccountNickname(), "감자");
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
