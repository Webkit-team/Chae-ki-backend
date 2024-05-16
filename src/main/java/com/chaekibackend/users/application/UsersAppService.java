package com.chaekibackend.users.application;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.S3Service;
import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersAppService {
    private final UsersService usersService;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;

    public UsersResponse.Detail readUser(Long uno) {
        Users fountUser = usersService.readByNo(uno);

        return UsersResponse.Detail.from(fountUser);
    }

    public UsersResponse.Delete deleteUser(Long uno) {
        Users fountUser = usersService.deleteUser(uno);

        return UsersResponse.Delete.from(fountUser);
    }

    public UsersResponse.Update updateUser(Long uno, UsersRequest.Update user, MultipartFile file) {
        // uno로 유저 정보 조회
        Users fountUser = usersService.readByNo(uno);

        // 수정할 정보로 덮어쓰기
        if (! user.getNickname().isBlank()) {
            fountUser.setNickname(user.getNickname());
        }
        if (! user.getPassword().isBlank()) {
            String password = passwordEncoder.encode(user.getPassword());
            fountUser.setPassword(password);
        }
        if (file != null && !file.isEmpty()) {
            String imageUrl = s3Service.saveFile(file);
            fountUser.setImageUrl(imageUrl);
        }

        // 수정된 유저정보를 저장
        Users userToUpdate = usersService.saveUser(fountUser);

        return UsersResponse.Update.from(userToUpdate);
    }

    public UsersResponse.Create signup(UsersRequest.Create user, MultipartFile file) {
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            imageUrl = s3Service.saveFile(file);
        }

        Users savedUser = usersService.signup(user, imageUrl);

        return UsersResponse.Create.from(savedUser);
    }

    public List<UsersResponse.Detail> readRankOfUsers(){
        List<UsersResponse.Detail> readUsersRank = new ArrayList<>();
        List<Users> usersRanking = usersService.readRankOfUsers();

        for(Users user : usersRanking){
            readUsersRank.add(UsersResponse.Detail.from(user));
        }

        return readUsersRank;
    }
}
