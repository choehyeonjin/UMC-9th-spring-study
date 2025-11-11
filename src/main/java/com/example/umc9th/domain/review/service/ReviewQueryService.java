package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponse> getMyReviews(Long memberId, String storeName, Integer ratingBand) {
        return reviewRepository.findMyReviews(memberId, storeName, ratingBand);
    }
}
