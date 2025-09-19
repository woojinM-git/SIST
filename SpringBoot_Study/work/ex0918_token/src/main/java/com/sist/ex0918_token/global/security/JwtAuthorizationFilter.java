package com.sist.ex0918_token.global.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{
    // 이 객체는 인가처리하는 객체임
    // ※ 인가처리 : 인증된 사용자가 특정 자원이나 서비스에 접근할 수 있는
                        // 권한이 있는지 확인하고 부여하는 절차

    @Override
    @SneakyThrows // try~catch로 예외처리를 해야 할 것을.. 명시적 예외처리를
                // 생략할 수 있도록 해준다
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        if(request.getRequestURI().equals("/api/members/login") || 
            request.getRequestURI().equals("/api/members/logout")){
                filterChain.doFilter(request, response);
                return;
            } // 로그인 또는 로그아웃은 통과
        // accessToken검증과 refreshToken 발급

        // 쿠키에 있는 accessToken을 빼 와서 확인하자
            // verify access 토큰 검사
            // 만약 aceess가 만료라면 DB에 refresh 토큰 검사
            // 또 만약 refresh까지 만료라면 
            // 다시 로그인하도록 유도
        String accessToken = null; // 좀있다가 하자
        if(!accessToken.isBlank()){ 
            // 나중에

        }
        filterChain.doFilter(request, response);
    }

}
