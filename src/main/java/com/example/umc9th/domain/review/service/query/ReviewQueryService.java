package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import java.util.List;

public interface ReviewQueryService {

    List<ReviewResDTO> getMyReviews(Long memberId, String storeName, Integer ratingBand);
}
