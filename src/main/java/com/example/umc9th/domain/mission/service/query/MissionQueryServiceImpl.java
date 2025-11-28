package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    private static final int PAGE_SIZE = 10;

    // 특정 가게의 미션 목록
    @Transactional(readOnly = true)
    @Override
    public MissionResDTO.MissionListDTO getStoreMissions(
            Long storeId,
            Integer page
    ) {
        // 가게 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 페이징 설정
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 해당 가게의 미션 목록 조회
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        // 미션이 없다면 예외
        if (result.isEmpty()) {
            throw new MissionException(MissionErrorCode.NOT_FOUND);
        }

        return MissionConverter.toMissionListDTOFromMissionPage(result);
    }

    // 내가 진행중인 미션 목록
    @Transactional(readOnly = true)
    @Override
    public MissionResDTO.MissionListDTO getMyInProgressMissions(
            Long memberId,
            Integer page
    ) {
        // 페이징 설정
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 진행중(IN_PROGRESS) 상태의 미션만 조회
        Page<MemberMission> result = memberMissionRepository.findAllByMember_IdAndStatus(
                memberId,
                MissionStatus.IN_PROGRESS,
                pageRequest
        );

        // 미션이 없다면 예외
        if (result.isEmpty()) {
            throw new MissionException(MissionErrorCode.NOT_FOUND);
        }

        return MissionConverter.toMissionListDTOFromMemberMissionPage(result);
    }
}
