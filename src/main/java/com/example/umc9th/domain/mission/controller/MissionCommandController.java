package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionCommandController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challenge(
            @RequestBody MissionReqDTO.ChallengeDTO dto
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                missionCommandService.challenge(dto)
        );
    }
}
