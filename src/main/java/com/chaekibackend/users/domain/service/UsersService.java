package com.chaekibackend.users.domain.service;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public Users signup(UsersRequest.Create user) {
        UsersRequest.Create encoded = UsersRequest.Create.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .nickname(user.getNickname())
                .imageString(user.getImageString())
                .build();
        Users saving = Users.from(encoded);

        return usersRepository.save(saving);
    }
}
