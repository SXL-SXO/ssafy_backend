package com.example.enjoytrip.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //로그인
    UnGrantAccount(HttpStatus.UNAUTHORIZED, "권한이 없는 사용자입니다"),
    LoginFail(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호를 확인하세요"),
    //회원가입
    DuplicateUserEmail(HttpStatus.CONFLICT, "가입이력이 있는 이메일입니다."),
    //회원정보조회
    UserNotFoundException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    //기타
    AccountCallFail(HttpStatus.BAD_REQUEST, "사용자 CRUD에서 오류가 났습니다."),
    //작성한 게시글 조회
    NotRegistWriteBoardAccount(HttpStatus.NOT_FOUND, "작성한 게시글이 없는 사용자입니다."),
    //작성한 댓글이 있는 게시글 조회
    NotRegistWriteCommentAccount(HttpStatus.NOT_FOUND, "작성한 댓글이 없는 사용자입니다."),
    //추천한 게시글 조회
    NotRegistRecommendBoardAccount(HttpStatus.NOT_FOUND, "좋아요한 게시글이 없는 사용자입니다."),


    InvalidSidoCode(HttpStatus.BAD_REQUEST, "유효하지 않은 시도코드입니다."),
    InvalidGugunCode(HttpStatus.BAD_REQUEST, "유휴하지 않은 구군코드입니다."),
    InvalidKeyword(HttpStatus.BAD_REQUEST, "유효하지 않은 키워드입니다."),
    invalidCoordinate(HttpStatus.BAD_REQUEST, "유효하지 않은 위치 정보입니다."),
    NotnullWeatherCoordinate(HttpStatus.BAD_REQUEST, "위치 정보는 필수 입력값입니다."),

    //게시글-파일업로드
    FileUploadFail(HttpStatus.BAD_REQUEST, "파일업로드에 실패했습니다."),
    FileCallFail(HttpStatus.BAD_REQUEST, "파일불러오기에 실패했습니다."),

    BoardCallFail(HttpStatus.BAD_REQUEST, "게시글 CRUD에서 오류가 났습니다."),
    ComentCallFail(HttpStatus.BAD_REQUEST, "댓글 CRUD에서 오류가 났습니다."),
    BoardRecommendFail(HttpStatus.BAD_REQUEST, "게시글 추천에서 오류가 났습니다."),
    SpotRecommendFail(HttpStatus.BAD_REQUEST, "장소 추천에서 오류가 났습니다.");



    //
    private final HttpStatus httpStatus;
    private final String errorMessage;
}
