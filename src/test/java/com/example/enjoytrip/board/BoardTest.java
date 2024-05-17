package com.example.enjoytrip.board;

import com.example.enjoytrip.board.dao.BoardDao;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.board.service.BoardService;
import com.example.enjoytrip.common.dto.PageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
class BoardTest {

    @Autowired
    BoardDao boardDao;

    @Autowired
    BoardService boardService;

    BoardDto boardDto = null;
    PageDto pageDto = null;

    @Test
    @DisplayName("의존성 주입 테스트")
    void testDi() {
        assertNotNull(boardDao);
        assertNotNull(boardService);
    }

    @BeforeEach
    void beforeEach() {
        boardDto = new BoardDto();
        boardDto.setTouristspotId(1);
        boardDto.setBoardTitle("배고파");
        boardDto.setBoardContent("안된다");
        boardDto.setAccountId(1);
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

//    @Test
}
