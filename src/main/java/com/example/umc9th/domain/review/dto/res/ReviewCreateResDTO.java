package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewCreateResDTO(
        Long reviewId,
        Long storeId,
        Float rating,
        String content,
        LocalDateTime createdAt
) {}
