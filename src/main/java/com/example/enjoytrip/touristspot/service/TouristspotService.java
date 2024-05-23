package com.example.enjoytrip.touristspot.service;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import com.example.enjoytrip.touristspot.domain.TouristSpot;
import com.example.enjoytrip.touristspot.dto.TouristCoordinateDto;

import java.util.List;

public interface TouristspotService {
    List<TouristSpot> findAll(PageDto pageDto);
    List<BoardDto> relatedBoard(int touristSpotId, PageDto pageDto);
    List<TouristSpot> relatedTitle(String touristSpotTitle);
    List<TouristSpot> findByKeyword(String keyword, PageDto pageDto);
    Integer touristspotRecommendInsert(Integer touristspotId, Integer AccountId);
    Integer touristspotRecommendDelete(Integer touristspotId, Integer AccountId);
    List<Integer> touristspotRecommendList(Integer AccountId);
    Integer touristspotRecommendCount(Integer touristspotId);

    Integer touristspotRecommendUpdate(Integer touristspotId, String mbti);
}
