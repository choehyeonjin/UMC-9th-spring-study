package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MissionControllerDocs {

    @Operation(
            summary = "특정 가게의 미션 목록 조회 API By 헤이즐",
            description = "storeId에 해당하는 가게의 미션들을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionListDTO> getStoreMissions(
            @RequestParam Long storeId,
            @RequestParam @ValidPage Integer page
    );

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API By 헤이즐",
            description = "회원이 진행중인 미션들을 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionListDTO> getMyInProgressMissions(
            @PathVariable Long memberId,
            @RequestParam @ValidPage Integer page
    );
}
