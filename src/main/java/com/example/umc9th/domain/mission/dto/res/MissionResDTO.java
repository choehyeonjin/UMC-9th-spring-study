package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 도전하기 응답
    public record ChallengeDTO(
            Long memberMissionId,
            Long missionId,
            MissionStatus status,
            LocalDateTime expiresAt,
            LocalDateTime createdAt
    ) {}

    // 공통 미션 목록 응답 DTO
    @Builder
    public record MissionListDTO(
            List<MissionItemDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionItemDTO(
            Long missionId,
            String storeName,
            String missionCondition,
            LocalDate deadline,
            Integer point,
            MissionStatus status,
            LocalDateTime expiresAt
    ) {}
}
