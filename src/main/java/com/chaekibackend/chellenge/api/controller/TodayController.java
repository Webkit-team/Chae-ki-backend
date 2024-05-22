package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.ChaekiTodayRequest;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.application.TodayAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "채키 투데이 관리")
public class TodayController {
    private final TodayAppService todayAppService;

    // 오늘 채키투데이를 작성했는지를 반환하는 API
    @GetMapping("today/challenges/{cno}/users/{uno}")
    @Operation(summary = "채키 투데이 작성 여부 조회",
            description = "사용자의 오늘 채키투데이 작성 여부를 조회합니다.")
    public ChaekiTodayResponse.Posted checkUserPost(@PathVariable("cno") Long challengeNo, @PathVariable("uno") Long userNo) {
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

    @PutMapping("today/{todayNo}")
    @Operation(summary = "집중시간 저장",
            description = "종료된 타이머의 시간을 저장합니다.")
    public ResponseEntity<Void> saveTimerTime(
            @PathVariable Long todayNo,
            @RequestBody ChaekiTodayRequest.TimerSave request
    ) {
        todayAppService.saveTimer(todayNo, request);

        return ResponseEntity.ok().build();
    }

    // 메인 페이지 투데이 랭킹 조회
    @GetMapping("/today/list")
    public List<ChaekiTodayResponse.Detail> getTodayList() {
        return todayAppService.getTodayList();
    }

    // 채키 투데이 좋아요
    @PutMapping("/like/today/{todayNo}")
    public ChaekiTodayResponse.Like likeToday(
            @PathVariable("todayNo") Long todayNo,
            @RequestBody ChaekiTodayRequest.Like request
    ) {
        return todayAppService.likeToday(todayNo, request);
    }
}
