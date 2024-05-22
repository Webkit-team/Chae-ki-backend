package com.chaekibackend.configuration.security;

import com.chaekibackend.users.api.response.UsersResponse;
import com.chaekibackend.users.domain.entity.Users;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsersRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("커스텀로그인필터 작동");
        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // jwt 발급
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = customUserDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        String token = jwtUtil.createJwt(username, role, 1000*60*60L); // 만료시간 1시간 설정

        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        // 로그인 응답 dto 생성 후 응답 body 담기
        Users findUser = userRepository.findByUsername(username);
        UsersResponse.Login responseDto = UsersResponse.Login.from(findUser);
        String json = objectMapper.writeValueAsString(responseDto);
        response.getOutputStream().println(json);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print("로그인 실패");
    }
}
