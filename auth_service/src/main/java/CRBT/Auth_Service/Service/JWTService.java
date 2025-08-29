package CRBT.Auth_Service.Service;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import CRBT.Auth_Service.Model.AuthUser;

import java.util.Date;

@Service
public class JWTService {
    private final String SECRET = "MyJwtSecretKeyThatIsAtLeast256BitsLongForHS256Algorithm";
    private final long EXPIRATION = 86400000; // 24 hours

    public String generateToken(AuthUser authUser) {
        return Jwts.builder()
                .setSubject(authUser.getUsername())
                .claim("userId", authUser.getUsers_id())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Long extractUserId(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expired");
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}