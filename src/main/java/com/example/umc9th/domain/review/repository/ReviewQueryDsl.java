package com.example.umc9th.domain.review.repository;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewQueryDsl {

    Page<Review> findMyReviews(Long memberId, String storeName, Integer ratingBand, Pageable pageable);
}