package com.example.Shopito.Security;

import com.example.Shopito.Entities.users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final String secretKey = Base64.getUrlEncoder().encodeToString("your-256-bit-secret-your-256-bit-secret".getBytes());



    public String generateToken(users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(SignatureAlgorithm.HS256, Base64.getUrlDecoder().decode(secretKey))
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getUrlDecoder().decode(secretKey))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Integer extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getUrlDecoder().decode(secretKey))
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", Integer.class);
    }


    public boolean isTokenValid(String token, users user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername());
    }
}
