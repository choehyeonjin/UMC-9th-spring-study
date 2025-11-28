package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewQueryService {

    // 내가 작성한 리뷰 목록 API
    @Transactional(readOnly = true)
    ReviewResDTO.ReviewListDTO getMyReviews(
            Long memberId,
            String storeName,
            Integer ratingBand,
            Integer page
    );

    ReviewResDTO.ReviewListDTO findReview(
            String storeName,
            Integer page
    );
}
