package com.example.demo.configuration;

import com.example.demo.models.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    private final String secret = "secret";
    private final String issuer = "webflix";

    public String generateAccessToken(Utilisateur utilisateur) {
        return Jwts.builder()
                .setSubject(String.format("%s", utilisateur.courriel))
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
