package com.example.enjoytrip.touristspot.service;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import com.example.enjoytrip.touristspot.dao.TouristspotDao;
import com.example.enjoytrip.touristspot.domain.TouristSpot;
import com.example.enjoytrip.touristspot.dto.TouristCoordinateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = false)
@RequiredArgsConstructor
@Service
public class TouristspotServiceImpl implements TouristspotService{

    private final TouristspotDao touristspotDao;

    @Override
    public List<TouristSpot> findAll(PageDto pageDto) {
        return touristspotDao.findAll(pageDto);
    }

    @Override
    public List<BoardDto> relatedBoard(int touristSpotId, PageDto pageDto) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("touristSpotId", touristSpotId);
        map.put("pageDto", pageDto);
        return touristspotDao.relatedBoard(map);
    }

    @Override
    public List<BoardDto> relatedTitle(String touristSpotTitle) {
        return touristspotDao.relatedTitle(touristSpotTitle);

    }

    @Override
    public List<TouristSpot> findByKeyword(String keyword, PageDto pageDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("pageDto", pageDto);
        return touristspotDao.findByKeyword(map);
    }

    @Override
    public Integer touristspotRecommendInsert(Integer touristspotId, Integer accountId) {
        List<Integer> likeRecommend =  touristspotDao.touristspotRecommendList(accountId);
        for(Integer liketouristspotId : likeRecommend){
            if(liketouristspotId.equals(touristspotId)) return null;
        }
        return touristspotDao.touristspotRecommendInsert(touristspotId, accountId);
    }

    @Override
    public Integer touristspotRecommendDelete(Integer touristspotId, Integer accountId) {
        List<Integer> likeRecommend =  touristspotDao.touristspotRecommendList(accountId);
        for(Integer likeBoardId : likeRecommend){
            if(likeBoardId.equals(touristspotId)) return touristspotDao.touristspotRecommendDelete(touristspotId, accountId);
        }
        return null;
    }

    @Override
    public List<Integer> touristspotRecommendList(Integer accountId) {
        return touristspotDao.touristspotRecommendList(accountId);
    }

    @Override //추천 수 세기
    public int touristspotRecommendCount(Integer touristspotId) {
        return touristspotDao.touristspotRecommendCount(touristspotId);
    }

    @Override
    public Integer touristspotRecommendUpdate(Integer touristspotId, String mbti) {
        String mbti1 = mbti.substring(0, 1);
        String mbti2 = mbti.substring(1, 2);
        String mbti3 = mbti.substring(2, 3);
        String mbti4 = mbti.substring(3);

        // DAO에 각 부분을 전달하여 처리
        return touristspotDao.touristspotRecommendUpdate(touristspotId, mbti1, mbti2, mbti3, mbti4);
    }
}
