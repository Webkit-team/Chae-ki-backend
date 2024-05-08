package com.chaekibackend.users.domain.service;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public Users deleteUser(Long uno) {
        Optional<Users> result = usersRepository.findById(uno);
        if(result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.");
        }

        Users user = result.get();
        user.setExpired(true);

        return usersRepository.save(user);
    }

    public Users saveUser(Users users) {
        return usersRepository.save(users);
    }

    @Transactional
    public Users readUser(Long uno) {
        Optional<Users> foundUser = usersRepository.findById(uno);
        if (foundUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.");
        }
        return foundUser.get();
    }

    @Transactional
    public Users signup(UsersRequest.Create user, String imageUrl) {
        UsersRequest.Create encoded = UsersRequest.Create
                .builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .nickname(user.getNickname())
                .imageUrl(imageUrl)
                .build();
        Users saving = Users.from(encoded);

        return usersRepository.save(saving);
    }
}
