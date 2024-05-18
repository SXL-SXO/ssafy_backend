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
    public Integer boardRecommendInsert(Integer boardId, Integer accountId) {
        List<Integer> likeRecommend =  boardDao.boardRecommendList(accountId);
        for(Integer likeBoardId : likeRecommend){
            if(likeBoardId.equals(boardId)) return null;
        }
        return boardDao.boardRecommendInsert(boardId, accountId);
    }
    @Override
    public Integer boardRecommendDelete(Integer boardId, Integer accountId) {
        List<Integer> likeRecommend =  boardDao.boardRecommendList(accountId);
        for(Integer likeBoardId : likeRecommend){
            if(likeBoardId.equals(boardId)) return boardDao.boardRecommendDelete(boardId, accountId);
        }
        return null;
    }

    @Override
    public List<Integer> boardRecommendList(Integer accountId) {
        List<Integer> result =  boardDao.boardRecommendList(accountId);
        return boardDao.boardRecommendList(accountId);
    }

    @Override
    public int boardRecommendCount(Integer boardId) {
        return boardDao.boardRecommendCount(boardId);
    }
}
