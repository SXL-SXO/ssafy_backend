package com.example.enjoytrip.touristspot.service;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import com.example.enjoytrip.touristspot.dao.TouristspotDao;
import com.example.enjoytrip.touristspot.domain.TouristSpot;
import com.example.enjoytrip.touristspot.dto.TouristCoordinateDto;
import com.example.enjoytrip.touristspot.dto.TouristspotRecomendDto;
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
    public Integer touristspotRecommendInsert(TouristspotRecomendDto touristspotrecomenddto) {
        String mbti1 = touristspotrecomenddto.getMbti().substring(0, 1);
        String mbti2 = touristspotrecomenddto.getMbti().substring(1, 2);
        String mbti3 = touristspotrecomenddto.getMbti().substring(2, 3);
        String mbti4 = touristspotrecomenddto.getMbti().substring(3);

        List<Integer> likeRecommend =  touristspotDao.touristspotRecommendList(touristspotrecomenddto.getAccountId());
        for(Integer liketouristspotId : likeRecommend){
            if(liketouristspotId.equals(touristspotrecomenddto.getTouristspotId())) return null;
        }
        int result = touristspotDao.touristspotRecommendInsert(touristspotrecomenddto.getTouristspotId(), touristspotrecomenddto.getAccountId());
        if(result==1){
            return touristspotDao.touristspotRecommendUpdate(touristspotrecomenddto.getTouristspotId(), mbti1, mbti2, mbti3, mbti4);
        }
        return null;
    }

    @Override
    public Integer touristspotRecommendDelete(TouristspotRecomendDto touristspotrecomenddto) {
        List<Integer> likeRecommend =  touristspotDao.touristspotRecommendList(touristspotrecomenddto.getAccountId());
        for(Integer likeBoardId : likeRecommend){
            if(likeBoardId.equals(touristspotrecomenddto.getTouristspotId())) {
                int result =  touristspotDao.touristspotRecommendDelete(touristspotrecomenddto.getTouristspotId(), touristspotrecomenddto.getAccountId());
                if(result==1){
                    touristspotDao.touristspotRecommendDelete(touristspotrecomenddto.getTouristspotId(), touristspotrecomenddto.getAccountId()); //이건 삭제 코드로 고쳐야함
                }
            }
        }
        return null;
    }

    @Override
    public List<Integer> touristspotRecommendList(Integer accountId) {
        return touristspotDao.touristspotRecommendList(accountId);
    }

    @Override //추천 수 세기
    public Integer touristspotRecommendCount(Integer touristspotId) {
        return touristspotDao.touristspotRecommendCount(touristspotId);
    }
}
