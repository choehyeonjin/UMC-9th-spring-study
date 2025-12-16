package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.UserType;
import com.example.umc9th.global.auth.enums.Role;

public class MemberConverter {

    // Entity -> JoinResDTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // JoinReqDTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .gender(dto.gender())
                .birthdate(dto.birthdate())
                .address(dto.address())
                .nickname(dto.nickname())

                // 기본값 세팅
                .userType(UserType.USER)
                .socialUid(null)
                .socialType(null) // 일반 회원가입이면 null
                .phone(null)
                .phoneVerified(false)
                .point(0)
                .build();
    }
}