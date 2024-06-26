package com.example.enjoytrip.touristspot.dao;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import com.example.enjoytrip.touristspot.domain.TouristSpot;
import com.example.enjoytrip.touristspot.dto.TouristCoordinateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface TouristspotDao {

    List<TouristSpot> findAll(PageDto pageDto);
    List<BoardDto> relatedBoard(Map<String, Object> map);
    List<TouristSpot> relatedTitle(String touristspotTitle);

    List<TouristSpot> findByKeyword(Map<String, Object> map);

    Integer touristspotRecommendInsert(Integer touristspotId, Integer accountId);
    Integer touristspotRecommendDelete(Integer touristspotId, Integer accountId);
    List<Integer> touristspotRecommendList(Integer accountId);
    Integer touristspotRecommendCount(Integer boardId);
    Integer touristspotRecommendUpdate(Integer touristspotId, String mbti1, String mbti2, String mbti3, String mbti4);

}
