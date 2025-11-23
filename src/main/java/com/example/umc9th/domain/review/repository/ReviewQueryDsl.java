package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;

import java.util.List;

public interface ReviewQueryDsl {
    List<ReviewResDTO> findMyReviews(Long memberId, String storeName, Integer ratingBand);
}