package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.request.ChallengeRequest;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.api.response.ChaekiTodayResponse;
import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.api.response.ReadingTimeResponse;
import com.chaekibackend.chellenge.application.ChallengeAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "챌린지 관리")
public class ChallengeController {
    private final ChallengeAppService challengeAppService;

    // 챌린지 등록
    @PostMapping(value="/challenge")
    @ResponseBody
    public List<ChallengeResponse.Detail> CreateChallenge(@RequestBody ChallengeRequest.Create request){
        return challengeAppService.createChallenge(request);
    }

    // 챌린지 상세 조회
    @GetMapping("/challenge/{no}")
    @ResponseBody
    public ChallengeResponse.Detail GetChallenge(@PathVariable("no") Long no){
        return challengeAppService.readChallenge(no);
    }

    // 마이페이지 챌린지 목록 조회
    @GetMapping("/users/{uno}/challenges")
    @ResponseBody
    public List<ChallengeResponse.Detail> GetMyChallenges(@PathVariable("uno") Long uno){
        return challengeAppService.readMyChallenges(uno);
    }

    // 마이페이지 채키투데이 목록 조회
    @GetMapping("users/{uno}/chaekiTodays")
    @ResponseBody
    public List<ChaekiTodayResponse.Detail> GetMyChaekiTodays(@PathVariable("uno") Long uno){
        return challengeAppService.readMyChaekiTodays(uno);
    }

    // 마이페이지 독서 시간 목록 조회 (그래프용)
    @GetMapping("users/{uno}/myReadingTime")
    @ResponseBody
    public List<ReadingTimeResponse> GetMyReadingTimes(@PathVariable("uno") Long uno){
        return challengeAppService.readMyReadingTimes(uno);
      
//    @PostMapping(value = "/challenges/timer/{uno}")
//    @Operation(summary = "타이머 시간 등록", description = "타이머에 설정 시간을 누적합니다.")
//    public Boolean saveTimer(@RequestParam Long uno, @RequestBody ChallengeRequest.TimerSave timer) {
//
//    }

//    @PostMapping(value = "/challenges/todays/{uno}",produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Operation(summary = "채키투데이 등록", description = "챌린지 참가자가 채키투데이를 등록합니다.")
//    public ChallengeResponse.TodaySave createChaekiToday()

    @PostMapping(value = "/challenges/members", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "챌린지 참가", description = "사용자가 특정 챌린지에 참가합니다.")
    public ChallengeResponse.Join joinChallenge(@RequestBody ChallengeRequest.Join join) {
        return challengeAppService.joinChallenge(join);
    }

    // todo: API 파라미터 설정하기
    @GetMapping("/challenges")
    @Operation(summary = "챌린지 목록 조회", description = "페이지의 요소 개수, 페이지 번호, 챌린지 상태, 도서명, 저자명을 기준으로 챌린지 목록을 조회합니다.")
    public Page<ChallengeResponse.Retrieval> readAllChallenges(
            // todo: ChallengeSort enum을 매개변수로 받아서 처리하기
            @Schema(type = "int", name = "page")
            @PageableDefault(page = 0, size = 10, sort = "startDate", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "RECRUITING") String status,
            @RequestParam(required = false) String category, // 전달된 값이 없을 시, null 할당됨
            @RequestParam(required = false) String searchQuery // 전달된 값이 없을 시, null 할당됨
    ) {
        if(category != null) {
            category = category.trim();
        }
        if(searchQuery != null) {
            searchQuery = searchQuery.trim();
        }

        return challengeAppService.readAllChallenges(pageable, status, category, searchQuery);
    }
}
