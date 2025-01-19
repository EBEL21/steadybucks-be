package app.ebel.steadybucks.security;

import app.ebel.steadybucks.aop.annotation.RequiresClanRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ClanAuthorizationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final List<HandlerMapping> handlerMappings;

    public ClanAuthorizationFilter(JwtTokenProvider jwtTokenProvider, List<HandlerMapping> handlerMappings) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.handlerMappings = handlerMappings;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return !path.startsWith("/api/clans/") || path.startsWith("/api/clans/create") || path.startsWith("/api/clans/list");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = JwtTokenProvider.resolveToken(request);

        if (token != null && JwtTokenProvider.validateToken(token)) {
            Claims claims = JwtTokenProvider.getClaims(token);

            System.out.println("클레임 출력: " + claims);

            String clanId = null;
            String requiredRole = "MEMBER";

            try {
//                if(request.getMethod().equalsIgnoreCase("GET") || request.getMethod().equalsIgnoreCase("DELETE")) {
//                    clanId = extractClanIdFromPath(request);
//                } else if(request.getMethod().equalsIgnoreCase("POST")) {
//                    clanId = extractClanIdFromBody(request);
//                }
                clanId = extractClanIdFromPath(request);
                HandlerMethod handlerMethod = getHandlerMethod(request);
                if (handlerMethod != null) {
                    RequiresClanRole annotation = handlerMethod.getMethodAnnotation(RequiresClanRole.class);
                    if (annotation != null) {
                        requiredRole = annotation.value();
                    }
                }

                System.out.println("요청온 클랜아이디: " + clanId);
                System.out.println("필요한 권한: " + requiredRole);
                if (clanId != null && !hasClanAuthorization(claims, clanId, requiredRole)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Forbidden: insufficient permissions for clan " + clanId);
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid request format");
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    private HandlerMethod getHandlerMethod(HttpServletRequest request) throws Exception {
        for (HandlerMapping handlerMapping : handlerMappings) {
            HandlerExecutionChain handlerChain = handlerMapping.getHandler(request);
            if (handlerChain != null && handlerChain.getHandler() instanceof HandlerMethod) {
                return (HandlerMethod) handlerChain.getHandler();
            }
        }
        return null;
    }

    private boolean hasClanAuthorization(Claims claims, String clanId, String requiredRole) {
        Map<String, String> clans = claims.get("clans", Map.class);
        if (clans == null) return false;

        if (Objects.equals(requiredRole, "MEMBER")) {
            return clans.containsKey(clanId);
        } else if (Objects.equals(requiredRole, "CAPTAIN")) {
            return Objects.equals(clans.get(clanId), requiredRole);
        }

        return false;
    }

    private String extractClanIdFromPath(HttpServletRequest request) {
        // PathVariable에서 clanId 추출
        // URI: /api/clans/{clanId}
        String[] pathParts = request.getRequestURI().split("/");
        return pathParts[3]; // 클랜 ID가 URL의 마지막 경로라고 가정
    }

    private String extractClanIdFromBody(HttpServletRequest request) throws IOException {
        // RequestBody를 읽어 clanId 추출
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = objectMapper.readValue(request.getInputStream(), Map.class);
        return (String) body.get("clanId"); // DTO에 clanId 필드가 있다고 가정
    }

    private String extractRequiredRoleFromRequest(HttpServletRequest request) {
        // 요청별로 필요한 역할을 결정 (예: OWNER, MEMBER)
        return "MEMBER"; // 예제에서는 OWNER 역할 필요
    }
}
