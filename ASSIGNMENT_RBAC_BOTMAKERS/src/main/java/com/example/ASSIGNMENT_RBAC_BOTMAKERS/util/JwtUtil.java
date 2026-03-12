package com.example.ASSIGNMENT_RBAC_BOTMAKERS.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private String SECRET_KEY = "TaK+HaV^uvC4HEFs3E2V4rpW#7230k*Z8$V";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token)  {
        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return  claimsJws.getPayload();
        }catch(UnsupportedJwtException e){
            throw new RuntimeException(e.getMessage()+"Exception thrown when receiving a JWT in a particular format/configuration that does not match the format expected by the application.");
        }catch(JwtException e){
            throw new RuntimeException(e.getMessage()+"if the jwt string cannot be parsed or validated as required.");
        }catch(IllegalArgumentException e){
            throw new RuntimeException(e.getMessage()+"if the jwt string is null or empty or only whitespace");
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1HOUR expiration time
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        return  (!isTokenExpired(token));
    }

}
