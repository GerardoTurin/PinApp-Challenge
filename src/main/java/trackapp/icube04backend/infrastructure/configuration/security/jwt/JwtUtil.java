package trackapp.icube04backend.infrastructure.configuration.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;


@Component
public class JwtUtil {

    private final String SECRET_KEY = "JSDJLKJLSAIOUO88587SAD87AS6D89ASD980798SAD3HJKHP9SDICKNSD";

    //private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora
    //private final long EXPIRATION_TIME = 1000 * 60 * 60 * 72; // 72 horas
    private final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 365; // 1 año

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // === NUEVO: generar token con authorities ===
    public String generateToken(String username, Collection<String> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authorities); // p.ej. ["SOMAS_SUPER_ADMIN"]
        return generateToken(claims, username);
    }

    // Overload genérico por si quieres meter más claims
    public String generateToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Valida firma y expiración
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractAuthorities(String token) {
        Object raw = parseClaims(token).getBody().get("authorities");
        if (raw instanceof List<?> list) {
            return list.stream().map(String::valueOf).toList();
        }
        return List.of();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
