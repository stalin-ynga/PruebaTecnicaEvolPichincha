package com.example.Experiencia.security;
 
import javax.crypto.spec.SecretKeySpec;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.Experiencia.dto.Auth.LoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
 
@Component
public class JwtToken {
 
     public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
     
     @Value("${jwt.secret}")
     private String secret ;
     
     public String generateToken(LoginResponse loginResponse) {
 
            Key hmacKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
            Instant now = Instant.now();
            String jwtToken = Jwts.builder()
                    .claim("username", loginResponse.getName())
                    .claim("id", loginResponse.getId())
                    .setSubject(loginResponse.getName())
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(Date.from(now))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(hmacKey)
                    .compact();
           
            return jwtToken;
        }  
   
}



