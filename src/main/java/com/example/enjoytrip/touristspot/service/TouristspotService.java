package com.example.enjoytrip.touristspot.service;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import com.example.enjoytrip.touristspot.domain.TouristSpot;
import com.example.enjoytrip.touristspot.dto.TouristCoordinateDto;
import com.example.enjoytrip.touristspot.dto.TouristspotRecomendDto;

import java.util.List;

public interface TouristspotService {
    List<TouristSpot> findAll(PageDto pageDto);
    List<BoardDto> relatedBoard(int touristSpotId, PageDto pageDto);
    List<BoardDto> relatedTitle(String touristSpotTitle);
    List<TouristSpot> findByKeyword(String keyword, PageDto pageDto);
    Integer touristspotRecommendInsert(TouristspotRecomendDto touristspotrecomenddto );
    Integer touristspotRecommendDelete(TouristspotRecomendDto touristspotrecomenddto);
    List<Integer> touristspotRecommendList(Integer AccountId);
    Integer touristspotRecommendCount(Integer touristspotId);

}
