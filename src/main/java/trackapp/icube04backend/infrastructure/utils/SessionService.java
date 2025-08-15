package trackapp.icube04backend.infrastructure.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService implements ISessionService{

    private final HttpServletRequest request;
    private static final String HEADER_COMPANY = "x-company-id";
    private static final String USER_TYPE = "usertype-id";

    @Value("1")
    private String tenantId;

    @Override
    public Long getCompanyId() {
        long companyId;
        try {
            companyId = Long.parseLong(request.getHeader(HEADER_COMPANY));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException("Invalid company ID in request header", e);
        }
        return companyId;
    }

    @Override
    public String getUrlBase() {
        return "";
    }

    @Override
    public List<Integer> getUsersCompanies() {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token no presente o incorrecto");
        }

        String token = header.replace("Bearer ", "");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor("JSDJLKJLSAIOUO88587SAD87AS6D89ASD980798SAD3HJKHP9SDICKNSD".getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("companies", List.class);
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public Long getUserTypeId() {
        long userTypeId;
        try {
            userTypeId = Long.parseLong(request.getHeader(USER_TYPE));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException("Invalid user type ID in request header", e);
        }
        return userTypeId;
    }

    @Override
    public Long getUserId() {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token no presente o incorrecto");
        }
        String token = header.substring("Bearer ".length());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor("JSDJLKJLSAIOUO88587SAD87AS6D89ASD980798SAD3HJKHP9SDICKNSD"
                        .getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();

        Number userId = claims.get("id", Number.class);
        if (userId == null) {
            throw new IllegalArgumentException("ID de usuario no encontrado en token");
        }
        return userId.longValue();
    }
}
