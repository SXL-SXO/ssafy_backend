package com.example.enjoytrip.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DuplicateUserEmail(HttpStatus.CONFLICT, "가입이력이 있는 이메일입니다."),
    UserNotFoundException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    InvalidSidoCode(HttpStatus.BAD_REQUEST, "유효하지 않은 시도코드입니다."),
    InvalidGugunCode(HttpStatus.BAD_REQUEST, "유휴하지 않은 구군코드입니다."),
    InvalidKeyword(HttpStatus.BAD_REQUEST, "유효하지 않은 키워드입니다."),
    invalidCoordinate(HttpStatus.BAD_REQUEST, "유효하지 않은 위치 정보입니다."),
    NotnullWeatherCoordinate(HttpStatus.BAD_REQUEST, "위치 정보는 필수 입력값입니다."),

    FileUploadFail(HttpStatus.BAD_REQUEST, "파일업로드에 실패했습니다.");
    private final HttpStatus httpStatus;
    private final String errorMessage;
}
