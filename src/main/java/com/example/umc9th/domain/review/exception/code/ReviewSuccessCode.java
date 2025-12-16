package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰 정보를 조회했습니다."),

    LIST_FOUND(HttpStatus.OK,
            "REVIEW200_2",
            "성공적으로 리뷰 목록을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
