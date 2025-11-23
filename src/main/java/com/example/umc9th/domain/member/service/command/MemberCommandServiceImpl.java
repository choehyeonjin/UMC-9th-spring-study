package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.repository.FoodRepository;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // Member 생성
        Member member = MemberConverter.toMember(dto);
        // DB 저장
        memberRepository.save(member);

        // 선호 음식 연결
        List<MemberFood> list = dto.preferFood().stream()
                .map(id -> MemberFood.builder()
                        .member(member)
                        .food(foodRepository.getReferenceById(id))
                        .build())
                .toList();

        // DB 저장
        memberFoodRepository.saveAll(list);

        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}
