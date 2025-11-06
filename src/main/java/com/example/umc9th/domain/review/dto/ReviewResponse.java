package com.example.umc9th.domain.review.dto;

import java.time.LocalDateTime;
public record ReviewResponse(
        Long reviewId,
        String storeName,
        Float rating,
        String content,
        LocalDateTime createdAt,
        String replyContent
) {}
