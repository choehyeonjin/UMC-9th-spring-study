package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewQueryDsl {
    List<ReviewResponse> findMyReviews(Long memberId, String storeName, Integer ratingBand);
}