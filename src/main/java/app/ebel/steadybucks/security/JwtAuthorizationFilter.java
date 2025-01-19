package app.ebel.steadybucks.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final List<String> allowedURI = List.of("/api/users/register", "/api/users/login", "/swagger-ui/", "/v3/api-docs");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return allowedURI.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = JwtTokenProvider.resolveToken(request);

        System.out.println("JwtAuthorizationFilter 작동합니다");

        // 토큰 인증 실패
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Token is missing or invalid\"}");
            return;
        }

        // 유효하지 않은 토큰
        if (!JwtTokenProvider.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Token is invalid\"}");
            return;
        }

        // JWT에서 사용자 권한을 추출하고 SecurityContext에 설정
        Authentication authentication = JwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
