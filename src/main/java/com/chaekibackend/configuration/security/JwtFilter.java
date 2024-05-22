package com.chaekibackend.configuration.security;

import com.chaekibackend.users.domain.entity.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("jwt 필터 작동");
        // request에서 Authorization 헤더 조회
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("JWT가 없습니다.");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 미인증 에러를 응답(401)
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("JWT가 없습니다.");
            return;
        }

        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            log.error("만료된 토큰");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 미인증 에러를 응답
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("만료된 JWT입니다.");
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //Users 엔티티 생성
        Users user = Users.builder()
                .username(username)
                .role(role)
                .build();

        // UserDetails에 회원정보객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        //스프링 시큐리티 인증토큰(인증객체) 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken); // 세션에 사용자 등록

        filterChain.doFilter(request, response); // 다음 필터를 진행
    }
}
