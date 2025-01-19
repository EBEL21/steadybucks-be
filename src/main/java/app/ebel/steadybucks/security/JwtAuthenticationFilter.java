package app.ebel.steadybucks.security;

import app.ebel.steadybucks.dto.request.LoginRqDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;

        this.setAuthenticationSuccessHandler(new LoginAuthSuccessHandler());
        this.setAuthenticationFailureHandler(new LoginAuthFailureHandler());
        setFilterProcessesUrl("/api/users/login"); // 로그인 엔드포인트 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("필터가 인증 시도!");

        try {
            LoginRqDto loginRqDto = new ObjectMapper().readValue(request.getInputStream(), LoginRqDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRqDto.getId(), loginRqDto.getPassword());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
