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

    // 내가 쓴 리뷰 조회
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<List<ReviewResDTO>> myReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingBand
    ) {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        // 응답 데이터 정의
        List<ReviewResDTO> data = reviewQueryService.getMyReviews(memberId, storeName, ratingBand);
        // code+message, result 응답
        return ApiResponse.onSuccess(code, data);
    }

    // 가게의 리뷰 목록 조회
    @Override
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
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
