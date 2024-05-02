package com.chaekibackend.users.domain.service;

import com.chaekibackend.users.domain.interfaces.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
}
