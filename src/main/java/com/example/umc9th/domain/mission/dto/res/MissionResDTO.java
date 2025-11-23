package com.example.umc9th.domain.mission.dto.res;

import com.example.umc9th.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;

public class MissionResDTO {

    // 미션 도전하기 응답
    public record ChallengeDTO(
            Long memberMissionId,
            Long missionId,
            MissionStatus status,
            LocalDateTime expiresAt,
            LocalDateTime createdAt
    ) {}
}
