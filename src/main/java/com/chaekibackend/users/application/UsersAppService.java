package com.chaekibackend.users.application;

import com.chaekibackend.book.domain.entity.Book;
import com.chaekibackend.book.domain.service.BookService;
import com.chaekibackend.users.api.request.UsersRequest;
import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.service.S3Service;
import com.chaekibackend.users.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final BookService bookService;

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
        List<Users> usersRanking = usersService.readRankOfUsers();
        List<Users> sliced = usersRanking.subList(0, Math.min(usersRanking.size(), 3));

        return sliced
                .stream()
                .map(UsersResponse.Detail::from)
                .toList();
    }

    public UsersResponse.Duplication checkDuplication(String username) {
        Boolean canUse = usersService.checkDuplication(username);

        return UsersResponse.Duplication
                .builder()
                .available(canUse)
                .build();
    }

    public UsersResponse.FavoriteBooks getFavoriteBooks(Long userNo) {
        Users user = usersService.readByNo(userNo);
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<Book> favoriteBooks = bookService.getFavoriteBooks(userNo, pageable);

        List<UsersResponse.FavoriteBookDetail> resultBooks = favoriteBooks.get()
                .map(UsersResponse.FavoriteBookDetail::from)
                .toList();

        return UsersResponse.FavoriteBooks
                .builder()
                .userNo(user.getNo())
                .nickName(user.getNickname())
                .books(resultBooks)
                .build();
    }
}
