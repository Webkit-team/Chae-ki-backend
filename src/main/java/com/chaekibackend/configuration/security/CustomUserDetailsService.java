package com.chaekibackend.configuration.security;

import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService { // DB의 사용자정보를 UserDetails 객체로 반환하는 역할
    private final UsersRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 매개변수 username은 제대로 전달됨
        Users user = userRepository.findByUsername(username); // DB에서 사용자 조회
        // user 변수가 null이 되는 것이 해결할 문제임
        // DB에는 해당하는 username을 가진 row가 존재함
        if(user == null) {
            log.error("DB에 해당 사용자가 없음");
            throw new UsernameNotFoundException("No user found");
        }

        return new CustomUserDetails(user);
    }
}
