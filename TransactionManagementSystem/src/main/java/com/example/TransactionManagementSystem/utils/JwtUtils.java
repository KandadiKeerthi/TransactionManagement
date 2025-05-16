package com.example.TransactionManagementSystem.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtils {
    private static final String secret = "mysecretkey";

    public static String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token, UserDetails userDetails){
        try {
            final String username = extractUsername(token);
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return (username.equals(userDetails.getUsername()));
        } catch (Exception e){
            return false;
        }
    }
}
