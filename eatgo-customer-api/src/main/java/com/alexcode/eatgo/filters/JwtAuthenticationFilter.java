package com.alexcode.eatgo.filters;

import com.alexcode.eatgo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * HTTP 요청에 들어 있는 Authorization 헤더를 필터링하여
 * 성공적이면 Authentication 객체를 SecurityContextHolder 객체에 저장
 */

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    /**
     * 생성된 Authentication 객체가 존재하면
     * SecurityContextHolder에 담음
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        Authentication authentication = getAuthentication(request);
        if(authentication != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    /**
     * HTTP 요청의 Authorization 헤더를 검사하여, 토큰에 저장된 Claims들을 가져옴
     * UsernamePasswordAuthentication의 principal에 Claims를 전달하여,
     * 이 정보를 Authentication 객체에 담음
     *
     * @param request
     * @return authentication
     */

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null) {
            return null;
        }

        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);
        return authentication;
    }
}
