package com.example.umc9th.domain.review.dto.res;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewResDTO {

    // 단건 조회용
    private Long reviewId;
    private String storeName;
    private Float rating;
    private String content;
    private LocalDateTime createdAt;
    private String replyContent;

    // 공통 목록 응답 DTO
    @Builder
    public record ReviewListDTO(
            List<ReviewItemDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record ReviewItemDTO(
            Long reviewId,
            String storeName,
            Float rating,
            String content,
            LocalDateTime createdAt,
            String replyContent
    ) {}
}
