package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;

    // 1) 리뷰 작성 쿼리
    @Transactional
    public Long writeReview(Long memberId, Long storeId, float rating, String content) {
        boolean ok = memberMissionRepository.existsSuccessAtStore(memberId, storeId); // 리뷰 작성 전에 성공 미션 존재 여부 확인
        if (!ok) throw new IllegalStateException("해당 가게 성공 미션이 없어 리뷰 작성 불가");

        // 성공 미션이 존재하면 리뷰 작성
        Review review = Review.builder()
                .member(Member.builder().id(memberId).build())
                .store(Store.builder().id(storeId).build())
                .rating(rating)
                .content(content)
                .build();
        return reviewRepository.save(review).getId();
    }
}
