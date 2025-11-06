package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 1) 리뷰 작성 쿼리: 리뷰 작성 여부 확인
    boolean existsByMember_IdAndStore_Id(Long memberId, Long storeId);
}