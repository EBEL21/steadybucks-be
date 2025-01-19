package app.ebel.steadybucks.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final byte[] SECRET_KEY = System.getenv("JWT_SECRET_KEY").getBytes();
    private static final long EXPIRATION_TIME = 86400000;

    // JWT 토큰 생성
    public static String generateToken(Authentication authentication) {

        sbUserDetails userDetails = (sbUserDetails) authentication.getPrincipal();

        System.out.println(userDetails);

        Map<Long, String> clanRoles = new HashMap<>();
        userDetails.getUserClanDtos().forEach(
                userClanDto -> clanRoles.put(userClanDto.getClanId(), userClanDto.getRole().name())
        );

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("userId", userDetails.getUser().getId())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .claim("clans", clanRoles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY))
                .compact();
    }

    public static Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        if (claims.get("roles") == null) {
            throw new RuntimeException("No role in token");
        }

        List<SimpleGrantedAuthority> authorities = ((List<String>) claims.get("roles")).stream()
                .map(SimpleGrantedAuthority::new) // ROLE_ 접두사 추가
                .toList();

        Long userId = claims.get("userId", Long.class);

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }

    // JWT 토큰에서 사용자 이름 추출
    public static String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public static boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // JWT 토큰 검증
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
