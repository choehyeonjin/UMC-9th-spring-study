package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewCreateResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(Member member, Store store, ReviewReqDTO.CreateDTO dto) {
        return Review.builder()
                .member(member)
                .store(store)
                .rating(Float.valueOf(dto.rating()))
                .content(dto.content())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ReviewPhoto toReviewPhoto(Review review, String url) {
        return ReviewPhoto.builder()
                .review(review)
                .url(url)
                .build();
    }

    public static ReviewCreateResDTO toCreateDTO(Review review) {
        return ReviewCreateResDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewListDTO toReviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewItemDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewItemDTO toReviewItemDTO(
            Review review
    ){
        return ReviewResDTO.ReviewItemDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .replyContent(
                        review.getReviewReply() == null
                                ? null
                                : review.getReviewReply().getContent()
                )
                .build();
    }
}
