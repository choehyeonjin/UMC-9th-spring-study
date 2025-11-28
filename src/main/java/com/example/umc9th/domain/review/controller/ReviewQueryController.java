package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ReviewQueryController implements ReviewControllerDocs {

    private final ReviewQueryService reviewQueryService;

    // 내가 작성한 리뷰 목록 API
    @Override
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewListDTO> myReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingBand,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        int pageIndex = page - 1;
        return ApiResponse.onSuccess(
                ReviewSuccessCode.LIST_FOUND,
                reviewQueryService.getMyReviews(memberId, storeName, ratingBand, pageIndex)
        );
    }

    // 가게의 리뷰 목록 API
    @Override
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
            ){
        int pageIndex = page - 1;
        ReviewSuccessCode code = ReviewSuccessCode.LIST_FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(
                storeName, pageIndex
        ));
    }
}
