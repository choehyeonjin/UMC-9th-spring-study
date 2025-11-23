package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewReply;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResDTO> findMyReviews(Long memberId, String storeName, Integer ratingBand) {

        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;
        QReviewReply reply = QReviewReply.reviewReply;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // (고정) 내가 작성한
        builder.and(review.member.id.eq(memberId));

        // 가게 이름 필터링
        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.contains(storeName));
        }

        // 별점 구간 필터링
        if (ratingBand != null) {
            float lo = Math.max(1, Math.min(5, ratingBand));
            float hi = (lo == 5) ? 5.0f : lo + 1.0f;
            builder.and(review.rating.goe(lo));
            if (lo < 5) builder.and(review.rating.lt(hi));
        }

        return queryFactory
                .select(Projections.constructor(
                        ReviewResDTO.class,
                        review.id,
                        store.name,
                        review.rating,
                        review.content,
                        review.createdAt,
                        reply.content
                ))
                .from(review)
                .join(review.store, store) // 가게는 반드시 존재
                .leftJoin(review.reviewReply, reply) // 답글은 선택적
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
