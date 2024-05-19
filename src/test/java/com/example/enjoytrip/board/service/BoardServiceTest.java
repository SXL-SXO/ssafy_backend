package com.example.enjoytrip.board.service;

import com.example.enjoytrip.account.dao.AccountDao;
import com.example.enjoytrip.account.dto.AccountDto;
import com.example.enjoytrip.account.service.AccountService;
import com.example.enjoytrip.board.dao.BoardDao;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@AutoConfigureMockMvc
@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardDao boardDao;

    @Autowired
    BoardService boardService;

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountService accountService;

    BoardDto boardDto = null;
    AccountDto accountDto = null;
    PageDto pageDto = null;

    @Test
    @DisplayName("의존성 주입 테스트")
    void testDi() {
        assertNotNull(boardDao);
        assertNotNull(boardService);
    }

    @BeforeEach
    void beforeEach() {
        accountDto = new AccountDto();
        accountDto.setAccountNickname("테스트");
        accountDto.setAccountEmail("KHG@test.com");
        accountDto.setAccountPassword("1234");
        accountService.accountInsert(accountDto);

        boardDto = new BoardDto();
        boardDto.setTouristspotId(1);
        boardDto.setBoardTitle("배고파");
        boardDto.setBoardContent("안된다");
        boardDto.setAccountId(accountDto.getAccountId());
    }

    @Test
    @Transactional
    @DisplayName("board 등록 테스트 -- 닉네임저장완료")
    void testboardInsert() {
        // given
        BoardDto dto = new BoardDto();
        dto.setTouristspotId(1);
        dto.setBoardTitle("배고파");
        dto.setBoardContent("안된다");
        dto.setAccountId(1);

        //when, then
        assertEquals(boardService.boardInsert(dto), 1);
    }

    @Test
    @DisplayName("board 수정 테스트 -- 수정이 잘되는지")
    @Transactional
    void testboardUpdate() {
        //given
        boardService.boardInsert(boardDto);

        //when
        boardDto.setTouristspotId(1);
        boardDto.setBoardTitle("배고파");
        boardDto.setBoardContent("수정된다");
        System.out.println(boardDto);

        //then
        assertEquals(boardService.boardUpdate(boardDto), 1);
    }
//
//    @Test
//    @DisplayName("board 수정 테스트 -- 사용자 닉네임이 변경되었을때 글의 닉네임도 변경되는가")
//    @Transactional
//    void testboardUpdateNickname() {
//        //given
//        boardService.boardInsert(boardDto);
//
//        Integer id = 1;
//        String email = "test123@email.com";
//        String password = "pass";
//        String nickname = "daehoya";
//        AccountRole accountRole = AccountRole.USER;
//        var account = AccountTestUtil.createAccount(id, email, password, nickname, accountRole);
//
//        given(accountDao.update(any(Account.class))).willReturn(1);
//        accountService.update(account);
//
//        //when
//        boardDto.setBoardTouristspotId(1);
//        boardDto.setBoardTitle("배고파");
//        boardDto.setBoardContent("수정된다");
//
//        //then
//        assertEquals(boardService.boardUpdate(boardDto), 1);
//        //assertEquals(boardDto.getBoardAccountNickname(), "daeho");
//    }

    @Test
    @DisplayName("board 삭제 테스트")
    @Transactional
    void testboardDelete() {
        //given
        boardService.boardInsert(boardDto);

        //when, then
        assertEquals(boardService.boardDelete(boardDto.getBoardId()), 1);
    }

    @Test
    @DisplayName("board 조회 테스트 -- 페이지로 게시글을 나눠서 일부만 가져오기")
    @Transactional
    void testboartList() {
        //given
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);
        boardService.boardInsert(boardDto);

        //when
        pageDto = new PageDto(3, 1);

        //then
        assertEquals(boardService.boardList(pageDto).size(), 3);
    }

    @Test
    @DisplayName("board 상세조회 테스트 -- 특정 글에 대한 내용 확인")
    @Transactional
    void testboartDetail() {
        //given
        boardService.boardInsert(boardDto);

        //then
        assertEquals(boardService.boardDetail(boardDto.getBoardId()).getBoardId(), boardDto.getBoardId());
        assertEquals(boardService.boardDetail(boardDto.getBoardId()).getAccountId(), boardDto.getAccountId());
        assertEquals(boardService.boardDetail(boardDto.getBoardId()).getTouristspotId(), boardDto.getTouristspotId());
        assertEquals(boardService.boardDetail(boardDto.getBoardId()).getBoardTitle(), boardDto.getBoardTitle());
        assertEquals(boardService.boardDetail(boardDto.getBoardId()).getBoardContent(), boardDto.getBoardContent());
    }

    @Test
    @DisplayName("board 추천하기 테스트 - 처음 추천")
    @Transactional
    void FirstAccountRecommendBoardTest() {
        //given
        boardService.boardInsert(boardDto);

        //when, then
        assertEquals(boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId()), 1);
    }

    @Test
    @DisplayName("board 추천하기 테스트 - 추천했던거 다시 추천")
    @Transactional
    void SecondAccountRecommendBoardTest() {
        //given
        boardService.boardInsert(boardDto);

        //when
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());

        //then
        assertEquals(boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId()), null);
    }

    @Test
    @DisplayName("board 추천취소하기 테스트 - 추천했던거 추천취소")
    @Transactional
    void AccountRecommendBoardDeleteBeforeRecommendTest() {
        //given
        boardService.boardInsert(boardDto);
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());

        //when

        //then
        assertEquals(boardService.boardRecommendDelete(boardDto.getBoardId(), accountDto.getAccountId()), 1);
        assertEquals(boardService.boardRecommendList(accountDto.getAccountId()).size(), 0);
        assertEquals(boardService.boardRecommendCount(boardDto.getBoardId()), 0);
    }

    @Test
    @DisplayName("board 추천취소하기 테스트 - 추천안했던거 추천취소")
    @Transactional
    void AccountRecommendBoardDeleteDoNotRecommendTest() {
        //given
        boardService.boardInsert(boardDto);

        //when
        boardService.boardRecommendDelete(boardDto.getBoardId(), accountDto.getAccountId());

        //then
        assertEquals(boardService.boardRecommendDelete(boardDto.getBoardId(), accountDto.getAccountId()), null);
    }

    @Test
    @DisplayName("board 추천수 가져오기 테스트")
    @Transactional
    void AccountRecommendBoardCountTest() {
        //given
        boardService.boardInsert(boardDto);
        //when
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());
        accountDto.setAccountEmail("test2@test.com");
        accountService.accountInsert(accountDto);
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());
        accountDto.setAccountEmail("test3@test.com");
        accountService.accountInsert(accountDto);
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());

        //then
        assertEquals(boardService.boardRecommendCount(boardDto.getBoardId()), 3);
    }

    @Test
    @DisplayName("board 추천 게시글 리스트 가져오기 테스트 (기본정렬 최근시간순)")
    @Transactional
    void AccountRecommendBoardListTest() {
        List<Integer> ExpectrecommendBoard = new ArrayList<>();
        List<Integer> ActualrecommendBoard;

        //given
        boardService.boardInsert(boardDto);
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());
        ExpectrecommendBoard.add(boardDto.getBoardId());

        boardService.boardInsert(boardDto);
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());
        ExpectrecommendBoard.add(boardDto.getBoardId());

        boardService.boardInsert(boardDto);
        boardService.boardRecommendInsert(boardDto.getBoardId(), accountDto.getAccountId());
        ExpectrecommendBoard.add(boardDto.getBoardId());

        //when
        ActualrecommendBoard = boardService.boardRecommendList(accountDto.getAccountId());

        //then
        assertEquals(ExpectrecommendBoard.size(), ActualrecommendBoard.size());
        for(int i=0;i<ExpectrecommendBoard.size();i++) {
            assertEquals(ExpectrecommendBoard.get(ExpectrecommendBoard.size()-1-i), ActualrecommendBoard.get(i));
        }
    }
}
