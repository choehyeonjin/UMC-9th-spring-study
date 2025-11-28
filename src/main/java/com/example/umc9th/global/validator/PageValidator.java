package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (value == null || value < 1) {

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("page는 1 이상이어야 합니다.")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
