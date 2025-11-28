package com.example.umc9th.domain.mission.dto.req;

public class MissionReqDTO {

    // 미션 도전하기 요청
    public record ChallengeDTO(
            Long missionId
    ) {}
}
