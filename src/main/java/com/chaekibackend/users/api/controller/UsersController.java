package com.chaekibackend.users.api.controller;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.application.UsersAppService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UsersAppService usersAppService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public ResponseEntity<UsersResponse.Create> signup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nickname,
            @RequestParam(required = false) MultipartFile image
    ) {
        UsersRequest.Create user = UsersRequest.Create.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
        try {
            UsersResponse.Create result = usersAppService.signup(user, image);
            return ResponseEntity.status(200).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

//    @PutMapping("/users/{uno}")
//    @Operation(summary = "회원수정", description = "사용자의 정보를 수정합니다.")
//    public UsersResponse.Update updateUser(@PathVariable Long uno, @RequestBody UsersRequest.Update user) {
//
//    }
}
