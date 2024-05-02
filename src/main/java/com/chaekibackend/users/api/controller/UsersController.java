package com.chaekibackend.users.api.controller;

import com.chaekibackend.users.application.UsersAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersAppService usersAppService;
}
