package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class MissionQueryController implements MissionControllerDocs {

    private final MissionQueryService missionQueryService;

    // 특정 가게의 미션 목록
    @Override
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        int pageIndex = page - 1;
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getStoreMissions(storeId, pageIndex)
        );
    }

    // 내가 진행중인 미션 목록
    @Override
    @GetMapping("/members/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyInProgressMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        int pageIndex = page - 1;
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMyInProgressMissions(memberId, pageIndex)
        );
    }
}
