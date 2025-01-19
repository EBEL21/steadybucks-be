package app.ebel.steadybucks.config;

import app.ebel.steadybucks.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final sbUserDetailService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final List<HandlerMapping> handlerMappings;

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter();

        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (API 환경에서 주로 사용)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/users/register", "/api/users/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/clans/create").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(
                        logout -> logout.logoutUrl("/api/users/logout")
                                .logoutSuccessUrl("/api/users/login")
                                .permitAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthorizationFilter, JwtAuthenticationFilter.class)
                .addFilterAfter(new ClanAuthorizationFilter(jwtTokenProvider(), handlerMappings), JwtAuthorizationFilter.class);

        return http.build();
    }


    // JWT 필터 빈 등록

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
}
