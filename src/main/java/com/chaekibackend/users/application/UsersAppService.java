package com.chaekibackend.users.application;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersAppService {
    private final UsersService usersService;

    public UsersResponse.Create signup(UsersRequest.Create user) {
        Users saved = usersService.signup(user);

        return UsersResponse.Create.from(saved);
    }
}
