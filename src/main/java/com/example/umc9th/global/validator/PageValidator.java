package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        boolean isValid = value != null && value >= 1;

        if (!isValid) {
            // 기본 메시지 제거
            context.disableDefaultConstraintViolation();
            // GeneralErrorCode의 메시지로 덮어쓰기
            context.buildConstraintViolationWithTemplate(
                            GeneralErrorCode.INVALID_PAGE.getMessage()
                    )
                    .addConstraintViolation();
        }

        return isValid;
    }
}
