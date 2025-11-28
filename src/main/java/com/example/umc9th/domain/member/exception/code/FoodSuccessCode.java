package com.example.umc9th.domain.member.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "FOOD200_1",
            "성공적으로 음식 정보를 조회했습니다."),

    LIST_FOUND(HttpStatus.OK,
            "FOOD200_2",
            "성공적으로 음식 목록을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
