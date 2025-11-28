package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    // 1) 리뷰 작성 쿼리: 리뷰 작성 여부 확인
    boolean existsByMember_IdAndStore_Id(Long memberId, Long storeId);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}