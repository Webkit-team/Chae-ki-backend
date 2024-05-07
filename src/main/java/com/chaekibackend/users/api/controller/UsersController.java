package com.chaekibackend.users.api.controller;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.application.UsersAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersAppService usersAppService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public UsersResponse.Create signup(@RequestBody UsersRequest.Create user) {
        return usersAppService.signup(user);
    }

//    @PutMapping("/users/{uno}")
//    public UsersResponse.Update updateUser(@PathVariable String uno) {
//
//    }
}
