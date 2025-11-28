package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewCreateResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewPhoto;
import com.example.umc9th.domain.review.repository.ReviewPhotoRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 로그인 기능 없으므로 하드코딩 회원
    private static final Long FIXED_MEMBER_ID = 1L;

    @Override
    @Transactional
    public ReviewCreateResDTO createReview(ReviewReqDTO.CreateDTO dto) {

        // 1. 하드코딩된 회원 조회
        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 2. 가게 조회
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 3. 리뷰 엔티티 생성 & 저장
        Review review = reviewRepository.save(
                ReviewConverter.toReview(member, store, dto)
        );

        // 4. 사진 최대 3개까지 저장
        if (dto.photos() != null && !dto.photos().isEmpty()) {
            List<String> urls = dto.photos().stream().limit(3).toList();

            List<ReviewPhoto> photos = urls.stream()
                    .map(url -> ReviewConverter.toReviewPhoto(review, url))
                    .toList();

            reviewPhotoRepository.saveAll(photos);
        }

        // 5. 응답 DTO로 변환
        return ReviewConverter.toCreateDTO(review);
    }
}
