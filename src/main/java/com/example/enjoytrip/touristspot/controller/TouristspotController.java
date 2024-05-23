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
    public ResponseEntity<List<BoardDto>> relatedTitle(@PathVariable("touristSpotTitle") String touristSpotTitle) {
        List<BoardDto> boardDto = TouristspotService.relatedTitle(touristSpotTitle);
//        for(BoardDto b : boardDto) System.out.println(b);
        return ResponseEntity.ok().body(boardDto);
    }

    @GetMapping("/sido/{sidoCode}")
    public ResponseEntity<List<TouristSpot>> findBysido(@PathVariable("sidoCode") int sidoCode,
                                                        PageDto pageDto){
        List<TouristSpot> bySido = TouristspotService.findBySido(sidoCode, pageDto);

        return ResponseEntity.ok().body(bySido);
    }

    @GetMapping("/gugun/{gugunCode}")
    public ResponseEntity<List<TouristSpot>> findByGugun(@PathVariable("gugunCode") int gugunCode,
                                                         PageDto pageDto){
        List<TouristSpot> byGugun = TouristspotService.findByGugun(gugunCode, pageDto);

        return ResponseEntity.ok().body(byGugun);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<TouristSpot>> findByKeyword(@PathVariable("keyword") String keyword,
                                                           PageDto pageDto){
        System.out.println(keyword);
        List<TouristSpot> byKeyword = TouristspotService.findByKeyword(keyword, pageDto);

        return ResponseEntity.ok().body(byKeyword);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TouristSpot> getTravel(@PathVariable("id") Integer id) {
        TouristSpot touristSpot = TouristspotService.findById(id);

        return ResponseEntity.ok(touristSpot);
    }

    @GetMapping("/coordinate")
    public ResponseEntity<List<TouristSpot>> getTravelsByCoordinate(@RequestBody TouristCoordinateDto TouristCoordinateDto,
                                                                    PageDto pageDto) {

        List<TouristSpot> byCoordinates = this.TouristspotService.findByCoordinates(TouristCoordinateDto, pageDto);

        return ResponseEntity.ok().body(byCoordinates);
    }


    @PutMapping("/recommends/board/{touristspotId}") //좋아요 mbti update
    public int touristspotRecommendUpdate(@RequestBody Integer touristsopId, @RequestBody String mbti){
        return TouristspotService.touristspotRecommendUpdate(touristsopId, mbti);
    }
}
