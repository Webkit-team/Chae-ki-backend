package com.chaekibackend.users.application;

import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.S3Service;
import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UsersAppService {
    private final UsersService usersService;
    private final S3Service s3Service;

//    public UsersResponse.Update updateUser(Long uno, UsersRequest.Update user) {
//
//    }

    public UsersResponse.Create signup(UsersRequest.Create user, MultipartFile file) throws IOException {
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            // 파일을 S3에 업로드하고 URL을 반환받음
            imageUrl = s3Service.saveFile(file);
        }

        // 사용자 정보를 저장. 이미지 URL이 있다면 해당 URL도 함께 저장
        Users savedUser = usersService.signup(user, imageUrl);

        // 성공적으로 사용자 등록이 완료되었음을 응답
        return UsersResponse.Create.from(savedUser);
    }
}
