package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 내가 작성한 리뷰 목록 API
    @Transactional(readOnly = true)
    @Override
    public ReviewResDTO.ReviewListDTO getMyReviews(
            Long memberId,
            String storeName,
            Integer ratingBand,
            Integer page
    ){
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findMyReviews(memberId, storeName, ratingBand, pageRequest);

        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.NOT_FOUND);
        }

        return ReviewConverter.toReviewListDTO(result);
    }

    // 가게의 리뷰 목록 API
    @Override
    public ReviewResDTO.ReviewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        // 리뷰가 없다면 예외 터뜨린다
        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.NOT_FOUND);
        }

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewListDTO(result);
    }
}
