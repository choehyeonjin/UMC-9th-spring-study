package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {

    // 1) 내가 작성한 리뷰 목록 API
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API By 헤이즐",
            description = "로그인한 사용자가 작성한 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewListDTO> myReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer ratingBand,
            @RequestParam @ValidPage Integer page
    );

    // 2) 가게의 리뷰 목록 API
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 헤이즐",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam @ValidPage Integer page
    );
}
