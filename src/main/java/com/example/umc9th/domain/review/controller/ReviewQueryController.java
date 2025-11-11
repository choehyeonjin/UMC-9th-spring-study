package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/reviews")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping
    public List<ReviewResponse> myReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingBand
    ) {
        return reviewQueryService.getMyReviews(memberId, storeName, ratingBand);
    }
}
