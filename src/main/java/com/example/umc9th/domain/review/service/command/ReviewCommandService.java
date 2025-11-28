package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewCreateResDTO;

public interface ReviewCommandService {

    ReviewCreateResDTO createReview(ReviewReqDTO.CreateDTO dto);
}
