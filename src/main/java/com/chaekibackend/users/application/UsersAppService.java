package com.chaekibackend.users.application;

import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersAppService {
    private final UsersService usersService;
}
