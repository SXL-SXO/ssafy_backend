package com.example.enjoytrip.account.service;

import static com.example.enjoytrip.account.dto.AccountRole.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.enjoytrip.account.dao.AccountDao;
import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.dto.AccountMbti;
import com.example.enjoytrip.board.dao.BoardDao;
import com.example.enjoytrip.board.dto.BoardCategory;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.board.service.BoardService;
import com.example.enjoytrip.comment.dao.CommentDao;
import com.example.enjoytrip.comment.dto.CommentDto;
import com.example.enjoytrip.comment.service.CommentService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    AccountDao accountDao;
    @Autowired
    AccountService accountService;
    @Autowired
    BoardDao boardDao;
    @Autowired
    BoardService boardService;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentDao commentDao;

    @Autowired
    MockMvc mockMvc;

    AccountDto accountDto = null;
    BoardDto boardDto = null;
    CommentDto commentDto = null;

    @Transactional
    @BeforeEach
    void beforeEach() {
        accountDto = new AccountDto();
        accountDto.setAccountEmail("KHG111@ssafy.com");
        accountDto.setAccountPassword("1234");
        accountDto.setAccountRole(USER);
        accountDto.setAccountNickname("김싸피");

        boardDto = new BoardDto();
        boardDto.setTouristspotId(1);
        boardDto.setBoardTitle("글제목");
        boardDto.setBoardContent("글내용");
        boardDto.setBoardCategory(BoardCategory.REVIEW);

        commentDto = new CommentDto();
        commentDto.setCommentContent("댓글내용");

    }
    @Test
    @DisplayName("의존성 주입 테스트")
    void testDi() {
        assertNotNull(accountDao);
        assertNotNull(accountService);
    }


    @Test
    @Transactional
    @DisplayName("회원가입")
    void checkAccountInsert (){
        //give

        //when
        Integer result = accountService.accountInsert(accountDto);

        //then
        assertEquals(result,1);
    }
    @Test
    @Transactional
    @DisplayName("회원가입전 - 존재하는 이메일 체크 (존재X) ")
    void beforeInsertCheckEmailNotExist(){
        //when
        Integer result = accountService.findByEmail("KHG@ssafy.com");

        //then
        assertEquals(result,1);
    }
    @Test
    @Transactional
    @DisplayName("회원가입전 - 존재하는 이메일 체크 (존재O) ")
    void BeforeInsertCheckEmailExist(){
        //give
        accountService.accountInsert(accountDto);

        //when
        Integer result = accountService.findByEmail("ssafy@ssafy.com");

        //then
        assertEquals(result,null);
    }
    @Test
    @Transactional
    @DisplayName("회원정보확인 - 회원(존재O)")
    void AfterInsertCheckEmailExist(){
        //give
        accountService.accountInsert(accountDto);

        //when
        AccountDto result = accountService.findById(accountDto.getAccountId());

        //then
        assertEquals(result.getAccountEmail(),"KHG111@ssafy.com");
        assertEquals(result.getAccountPassword(),"1234");
        assertEquals(result.getAccountRole(),USER);
        assertEquals(result.getAccountNickname(),"김싸피");
    }

    @Test
    @Transactional
    @DisplayName("회원정보확인 - 회원(존재X)")
    void AfterInsertCheckEmailNotExist(){
        //give

        //when
        AccountDto result = accountService.findById(0);

        //then
        assertEquals(result,null);
    }

    @Test
    @Transactional
    @DisplayName("회원정보수정")
    void AccountUpdateTest(){
        //give
        accountService.accountInsert(accountDto);

        //when
        AccountDto updateAccount = accountService.findById(accountDto.getAccountId());
        updateAccount.setAccountNickname("감자");
        updateAccount.setAccountPassword("1111");
        updateAccount.setAccountMbti(AccountMbti.ENFP);

        //then
        assertEquals(updateAccount.getAccountPassword(),"1111");
        assertEquals(updateAccount.getAccountNickname(),"감자");
        assertEquals(updateAccount.getAccountMbti(),AccountMbti.ENFP);
    }

    @Test
    @Transactional
    @DisplayName("회원탈퇴")
    void AccountDeleteTest(){
        //give
        accountService.accountInsert(accountDto);

        //when
        accountService.accountDelete(accountDto.getAccountId());

        //then
        assertEquals(accountService.findById(accountDto.getAccountId()),null);
        assertEquals(accountService.findByEmail(accountDto.getAccountEmail()),1);
    }


//    @Test
//    @Transactional
//    @DisplayName("로그인테스트 - 존재하는 회원에 관하여")
//    void ExistAccountLoginTest(){
//        //give
//        accountService.accountInsert(accountDto);
//
//        //when
//        //then
//        assertEquals(accountDto.getAccountId(),accountService.login(accountDto).getAccountId());
//    }

//    @Test
//    @Transactional
//    @DisplayName("로그인테스트 - 존재하지 않는 회원에 관하여")
//    void NotExistAccountLoginTest(){
//        //give
//
//        //when
//        AccountDto result = accountService.login(accountDto);
//
//        //then
//        assertEquals(null,result);
//    }

    @Test
    @Transactional
    @DisplayName("본인이 작성한 게시글 확인")
    void AccountBoardTest(){
        List<Integer> ExpectedWriteBoard= new ArrayList<>();
        List<BoardDto> ActualWriteBoard;

        //give
        accountService.accountInsert(accountDto);
        boardDto.setAccountId(accountDto.getAccountId());
        boardDto.setAccountNickname(accountDto.getAccountNickname());
        boardService.boardInsert(boardDto);
        ExpectedWriteBoard.add(boardDto.getBoardId());
        boardService.boardInsert(boardDto);
        ExpectedWriteBoard.add(boardDto.getBoardId());
        boardService.boardInsert(boardDto);
        ExpectedWriteBoard.add(boardDto.getBoardId());

        //when
        ActualWriteBoard = accountService.accountBoard(accountDto.getAccountId());

        for(int i=0;i<ExpectedWriteBoard.size();i++){
            assertEquals(ExpectedWriteBoard.get(i),ActualWriteBoard.get(i).getBoardId());
        }
    }

    @Test
    @Transactional
    @DisplayName("본인이 작성한 댓글 확인")
    void AccountCommentTest(){
        List<Integer> ExpectedWriteComment = new ArrayList<>();
        List<BoardDto> ActualWriteComment;
        Integer accountId ;
        //give
        accountService.accountInsert(accountDto);
        boardDto.setAccountId(accountDto.getAccountId());
        boardDto.setAccountNickname(accountDto.getAccountNickname());
        boardService.boardInsert(boardDto);
        accountId = accountDto.getAccountId();

        commentDto.setAccountId(accountDto.getAccountId());
        commentDto.setBoardId(boardDto.getBoardId());
        commentService.commentInsert(commentDto);
        ExpectedWriteComment.add(boardDto.getBoardId());
        commentService.commentInsert(commentDto);
        ExpectedWriteComment.add(boardDto.getBoardId());

        boardService.boardInsert(boardDto);
        commentService.commentInsert(commentDto);
        ExpectedWriteComment.add(boardDto.getBoardId());

        accountDto.setAccountEmail("check");
        accountService.accountInsert(accountDto);
        commentDto.setAccountId(accountDto.getAccountId());
        commentDto.setAccountNickname(accountDto.getAccountNickname());
        commentService.commentInsert(commentDto);
        ExpectedWriteComment.add(boardDto.getBoardId());

        //when
        ActualWriteComment = accountService.accountComment(accountId);

        //then
        for(int i=0;i<ExpectedWriteComment.size();i++){
            assertEquals(ExpectedWriteComment.get(i),ActualWriteComment.get(i).getBoardId());
        }
        assertEquals(ExpectedWriteComment.size(), 3);
    }
}
