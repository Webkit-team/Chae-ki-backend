package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.ChaekiTodayRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.application.TodayAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "채키 투데이 관리")
public class TodayController {
    private final TodayAppService todayAppService;

    // 오늘 채키투데이를 작성했는지를 반환하는 API
    @GetMapping("today/challenges/{cno}/users/{uno}")
    @Operation(summary = "채키 투데이 작성 여부 조회",
            description = "사용자의 오늘 채키투데이 작성 여부를 조회합니다.")
    public Boolean checkUserPost(@PathVariable("cno") Long challengeNo, @PathVariable("uno") Long userNo) {
        return todayAppService.checkUserPost(challengeNo, userNo);
    }

    @PostMapping("today/challenges/{cno}/users/{uno}")
    @Operation(summary = "채키 투데이 등록",
            description = "채키투데이를 작성합니다.")
    public ChaekiTodayResponse.Creation registerToday(
            @PathVariable("cno") Long challengeNo,
            @PathVariable("uno") Long userNo,
            @RequestBody ChaekiTodayRequest.Creation request
    ) {
        return todayAppService.registerToday(challengeNo, userNo, request);
    }
}
