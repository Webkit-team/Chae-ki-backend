package com.chaekibackend.users.api.controller;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.application.UsersAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "회원관리")
public class UsersController {
    private final UsersAppService usersAppService;

    @DeleteMapping("/users/{uno}")
    @Operation(summary = "회원탈퇴", description = "회원탈퇴를 진행합니다.")
    public UsersResponse.Delete deleteUser(@PathVariable Long uno) {
        return usersAppService.deleteUser(uno);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public UsersResponse.Create signup(
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



        return usersAppService.signup(user, image);
    }

    @PutMapping("/users/{uno}")
    @Operation(summary = "회원수정", description = "사용자의 정보를 수정합니다.")
    public UsersResponse.Update updateUser(
            @PathVariable Long uno,
            @RequestParam String password,
            @RequestParam String nickname,
            @RequestParam(required = false) MultipartFile image
    ) {
        UsersRequest.Update users = UsersRequest.Update.builder()
                .password(password)
                .nickname(nickname)
                .build();

        return usersAppService.updateUser(uno, users, image);
    }
}
