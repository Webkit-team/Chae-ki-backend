package com.chaekibackend.configuration.security;

import com.chaekibackend.users.domain.interfaces.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final UsersRepository userRepository;

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // csrf 비활성화
        http.cors(customizer -> customizer.configurationSource(corsConfigurationSource())); // cors 설정
        http.formLogin(AbstractHttpConfigurer::disable); // form 안 쓰고, api 방식으로 할 거라서
        http.httpBasic(AbstractHttpConfigurer::disable); // header에 아이디,비번 안 넣고, jwt 사용할 거라서
        http.authorizeHttpRequests(auth -> auth // 경로마다 인가 작업
                .requestMatchers("/signup", "/userRank","/bookList", "/challenge", "/challenge/**",
                        "/users/*/challenges", "users/*/chaekiTodays", "users/*/myReadingTime",
                        "/challenge/books/{word}", "/books/{bno}").permitAll()
                .requestMatchers("/login", "/", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/challenges").permitAll()
                .anyRequest().authenticated());
        http.sessionManagement(session -> session // 세션을 비상태로 설정
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterAt(new CustomLoginFilter(authenticationManager(), jwtUtil, userRepository), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new JwtFilter(jwtUtil), CustomLoginFilter.class);
        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("*"); // 클라이언트의 주소
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위 설정 적용
        return source;
    }
}
