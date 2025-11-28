package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 로그인 대신 사용할 하드코딩 멤버
    private static final Long FIXED_MEMBER_ID = 1L;

    @Override
    @Transactional
    public MissionResDTO.ChallengeDTO challenge(MissionReqDTO.ChallengeDTO dto) {

        // 1) 멤버 조회 (하드코딩)
        Member member = memberRepository.findById(FIXED_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 2) 미션 존재 여부
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        // 3) 이미 도전 중인지 확인
        if (memberMissionRepository.existsByMemberIdAndMissionId(member.getId(), mission.getId())) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGED);
        }

        // 4) member_mission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .expiresAt(mission.getDeadline().atStartOfDay())
                .build();

        MemberMission saved = memberMissionRepository.save(memberMission);

        // 5) 응답 DTO
        return new MissionResDTO.ChallengeDTO(
                saved.getId(),
                mission.getId(),
                saved.getStatus(),
                saved.getExpiresAt(),
                saved.getCreatedAt()
        );
    }
}
