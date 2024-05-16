package com.chaekibackend.chellenge.api.controller;

import com.chaekibackend.chellenge.api.response.ChallengeResponse;
import com.chaekibackend.chellenge.application.ChallengeAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "챌린지 관리")
public class ChallengeController {
    private final ChallengeAppService challengeAppService;

    // todo: API 파라미터 설정하기
    @GetMapping("/challenges")
    @Operation(summary = "챌린지 목록 조회", description = "페이지의 요소 개수, 페이지 번호, 챌린지 상태, 도서명, 저자명을 기준으로 챌린지 목록을 조회합니다.")
    public Page<ChallengeResponse.Retrieval> readAllChallenges(
            // todo: ChallengeSort enum을 매개변수로 받아서 처리하기
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
