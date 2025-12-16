package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    // 특정 가게의 미션 목록
    public static MissionResDTO.MissionListDTO toMissionListDTOFromMissionPage(
            Page<Mission> result
    ) {
        return MissionResDTO.MissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionItemDTOFromMission)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionItemDTO toMissionItemDTOFromMission(
            Mission mission
    ) {
        return MissionResDTO.MissionItemDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionCondition(mission.getMissionCondition())
                .deadline(mission.getDeadline())
                .point(mission.getPoint())
                .status(null)
                .expiresAt(null)
                .build();
    }

    // 내가 진행중인 미션 목록
    public static MissionResDTO.MissionListDTO toMissionListDTOFromMemberMissionPage(
            Page<MemberMission> result
    ) {
        return MissionResDTO.MissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionItemDTOFromMemberMission)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionItemDTO toMissionItemDTOFromMemberMission(
            MemberMission memberMission
    ) {
        Mission mission = memberMission.getMission();

        return MissionResDTO.MissionItemDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionCondition(mission.getMissionCondition())
                .deadline(mission.getDeadline())
                .point(mission.getPoint())
                .status(memberMission.getStatus())
                .expiresAt(memberMission.getExpiresAt())
                .build();
    }
}
