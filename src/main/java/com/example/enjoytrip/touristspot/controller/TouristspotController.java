package com.example.enjoytrip.touristspot.controller;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.common.dto.PageDto;
import com.example.enjoytrip.touristspot.dto.TouristCoordinateDto;
import com.example.enjoytrip.touristspot.domain.TouristSpot;
import com.example.enjoytrip.touristspot.service.TouristspotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/touristspot")
public class TouristspotController {
    private final TouristspotService TouristspotService;

    @GetMapping
    public ResponseEntity<List<TouristSpot>> getTravels(PageDto pageDto) {
        List<TouristSpot> touristspots = TouristspotService.findAll(pageDto);

        return ResponseEntity.ok().body(touristspots);
    }

    @GetMapping("/relativeBoard/{touristSpotId}")
    public ResponseEntity<List<BoardDto>> relatedBoard(@PathVariable("touristSpotId") int touristSpotId, PageDto pageDto) {
        List<BoardDto> boardDto = TouristspotService.relatedBoard(touristSpotId, pageDto);

        return ResponseEntity.ok().body(boardDto);
    }
    @GetMapping("/relativeTitle/{touristSpotTitle}")
    public ResponseEntity<List<TouristSpot>> relatedTitle(@PathVariable("touristSpotTitle") String touristSpotTitle) {
        List<TouristSpot> touristspot = TouristspotService.relatedTitle(touristSpotTitle);
//        for(BoardDto b : boardDto) System.out.println(b);
        return ResponseEntity.ok().body(touristspot);
    }
    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<TouristSpot>> findByKeyword(@PathVariable("keyword") String keyword,
                                                           PageDto pageDto){
        System.out.println(keyword);
        List<TouristSpot> byKeyword = TouristspotService.findByKeyword(keyword, pageDto);

        return ResponseEntity.ok().body(byKeyword);
    }

    @PostMapping("/recommends") //좋아요
    public Integer touristspotRecommendInsert(@RequestBody Map<String, Integer> recomend){
        return TouristspotService.touristspotRecommendInsert(recomend.get("touristspotId"), recomend.get("accountId"));
    }

    @PostMapping("/recommends/delete") //좋아요 취소
    public Integer touristspotRecommendDelete(@RequestBody Map<String, Integer> recomend){
        return TouristspotService.touristspotRecommendDelete(recomend.get("touristspotId"), recomend.get("accountId"));
    }

    @GetMapping("/recommends/account/{accountId}") //사용자가 누른 좋아요 게시판 목록
    public List<Integer> touristspotRecommendList(@PathVariable Integer accountId){
        return TouristspotService.touristspotRecommendList(accountId);
    }
    @GetMapping("/recommends/board/{touristspotId}") //좋아요 갯수 카운트
    public int touristspotRecommendCount(@PathVariable Integer touristspotId){
        return TouristspotService.touristspotRecommendCount(touristspotId);
    }

    @PutMapping("/recommends/board/{touristspotId}") //좋아요 mbti update
    public int touristspotRecommendUpdate(@RequestBody Integer touristsopId, @RequestBody String mbti){
        return TouristspotService.touristspotRecommendUpdate(touristsopId, mbti);
    }
}
