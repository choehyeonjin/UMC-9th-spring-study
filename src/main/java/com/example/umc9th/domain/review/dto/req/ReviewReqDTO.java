package com.example.umc9th.domain.review.dto.req;

import java.util.List;

public class ReviewReqDTO {

    public record CreateDTO(
            Long storeId,
            Float rating,
            String content,
            List<String> photos
    ) {}
}
