package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewCreateResDTO;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<ReviewCreateResDTO> createReview(
            @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        GeneralSuccessCode code = GeneralSuccessCode.CREATED;

        ReviewCreateResDTO data = reviewCommandService.createReview(dto);

        return ApiResponse.onSuccess(code, data);
    }
}
