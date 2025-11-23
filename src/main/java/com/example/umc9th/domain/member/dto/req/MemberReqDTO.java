package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birthdate,
            String address,
            List<Long> preferFood // 선택한 음식 유형 ID 리스트
    ){}
}
