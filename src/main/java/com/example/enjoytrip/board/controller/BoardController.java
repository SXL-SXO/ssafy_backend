package com.example.enjoytrip.board.controller;

import com.example.enjoytrip.board.dto.BoardDto;
import com.example.enjoytrip.board.service.BoardService;
import com.example.enjoytrip.common.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final WebApplicationContext context;

    @GetMapping()
    public List<BoardDto> boardList(
            @RequestParam (required = false) Integer pageSize,
            @RequestParam (required = false) Integer pageNum,
            @RequestParam (required = false) String searchWord){
//        log.info("pageSize = {}", pageSize);
        PageDto pageDto = new PageDto(pageSize, pageNum, searchWord);
        List<BoardDto> list = boardService.boardList(pageDto);
        return list;
    }

    /*@GetMapping()
    public List<BoardDto> boardList(@RequestBody Map<String, String> option){
        log.info("requestbody = {}", option);
        Integer pageSize = Integer.parseInt(option.getOrDefault("pageSize","0"));
        Integer pageLimit = Integer.parseInt(option.getOrDefault("pageLimit","0"));
        PageDto pageDto = new PageDto(pageSize, pageLimit);
        List<BoardDto> list = boardService.boardList(pageDto);
        return list;
    }

    */
    @GetMapping("/{boardId}")
    public BoardDto boardDetail(@PathVariable("boardId") int boardId){
        BoardDto dto = boardService.boardDetail(boardId);
        return dto;
    }
    @PutMapping("/{boardId}")
    public Integer boardUpdate(@PathVariable("boardId") int boardId, BoardDto dto){
        return boardService.boardUpdate(dto);
    }
    @PostMapping
    public Integer boardInsert(BoardDto dto){
        return boardService.boardInsert(dto);
    }

    @DeleteMapping("/{boardId}")
    public Integer boardDelete(@PathVariable("boardId") int boardId){
        return boardService.boardDelete(boardId);
    }


    @PostMapping("/recommends")
    public Integer boardRecommendInsert(@RequestBody Map<String, Integer> recomend){
        return boardService.boardRecommendInsert(recomend.get("boardId"), recomend.get("accountId"));
    }

    @DeleteMapping("/recommends")
    public Integer boardRecommendDelete(@RequestBody Map<String, Integer> recomend){
        return boardService.boardRecommendDelete(recomend.get("boardId"), recomend.get("accountId"));
    }

    @GetMapping("/recommends/account")
    public List<Integer> boardRecommendList(@RequestBody Integer accountId){
        return boardService.boardRecommendList(accountId);
    }
    @GetMapping("/recommends/board")
    public int boardRecommendCount(@RequestBody Integer boardId){
        return boardService.boardRecommendCount(boardId);
    }

    @PostMapping("/image")
    // @RequestParam은 자바스크립트에서 설정한 이름과 반드시 같아야합니다.
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        try {
            // 서버에 저장할 경로
            String uploadDirectory = context.getServletContext().getRealPath("/resources/assets/images/upload");

            // 업로드 된 파일의 이름
            String originalFileName = file.getOriginalFilename();

            // 업로드 된 파일의 확장자
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // 업로드 될 파일의 이름 재설정 (중복 방지를 위해 UUID 사용)
            String uuidFileName = UUID.randomUUID().toString() + fileExtension;

            // 위에서 설정한 서버 경로에 이미지 저장
            file.transferTo(new File(uploadDirectory, uuidFileName));

            System.out.println("************************ 업로드 컨트롤러 실행 ************************");
            System.out.println(uploadDirectory);

            // Ajax에서 업로드 된 파일의 이름을 응답 받을 수 있도록 해줍니다.
            return ResponseEntity.ok(uuidFileName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("이미지 업로드 실패");
        }

    }
}
