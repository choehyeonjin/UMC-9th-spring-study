package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {

    // 특정 가게의 미션 목록
    MissionResDTO.MissionListDTO getStoreMissions(Long storeId, Integer page);

    // 내가 진행중인 미션 목록
    MissionResDTO.MissionListDTO getMyInProgressMissions(Long memberId, Integer page);
}
