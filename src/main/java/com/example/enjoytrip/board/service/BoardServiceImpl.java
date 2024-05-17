package com.example.enjoytrip.board.service;

import com.example.enjoytrip.board.dao.BoardDao;
import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.comment.dao.CommentDao;
import com.example.enjoytrip.comment.domain.CommentDto;
import com.example.enjoytrip.common.dto.PageDto;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final CommentDao commentDao;

    @Override
    public List<BoardDto> boardList(PageDto pageDto) {
        return boardDao.boardList(pageDto);
    }

    @Transactional
    @Override
    public BoardDto BoardDetailswithComment(Integer boardId) {
        BoardDto boardDto = boardDao.boardDetail(boardId);
        boardDto.setBoardComment(commentDao.commentList(boardId));
        return boardDto;
    }

    @Override
    public BoardDto boardDetail(Integer boardId) {
        return boardDao.boardDetail(boardId);
    }

    @Override
    public Integer boardInsert(BoardDto boardDto) {return boardDao.boardInsert(boardDto);}

    @Override
    public Integer boardUpdate(BoardDto boardDto) {
        return boardDao.boardUpdate(boardDto);
    }

    @Override
    public Integer boardDelete(Integer boardId) {
        return boardDao.boardDelete(boardId);
    }

    @Override
    public Integer boardRecommend(Integer boardId, Integer accountId, int boardRecommend) {
        List<BoardDto> likeRecommend =  boardDao.boardRecommendList(accountId));
        for(BoardDto boardDto : likeRecommend){
            if(boardDto.getBoardId().equals(boardId)) return null;
        }
        return 1;
    }

    @Override
    public List<BoardDto> boardRecommendList(Integer AccountId) {
        return boardDao.boardRecommendList(AccountId);
    }

    @Override
    public List<CommentDto> commentList(Integer boardId) {
        return commentDao.commentList(boardId);
    }
}
