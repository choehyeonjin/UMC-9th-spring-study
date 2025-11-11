package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/reviews")
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping
    public ApiResponse<List<ReviewResponse>> myReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingBand
    ) {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // 응답 데이터 정의
        List<ReviewResponse> data = reviewQueryService.getMyReviews(memberId, storeName, ratingBand);

        // code+message, result 응답
        return ApiResponse.onSuccess(code, data);
    }
}
