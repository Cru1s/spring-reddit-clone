/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cyberbugs.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Cruis
 */
public class JwtProvider {
    
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    
    public static String generateToken(Authentication auth){
        
        return Jwts.builder()
                .issuer("Cyberbugs").issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+8640000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }
    
    public static String getEmailFromJwtToken(String jwt){
        
        jwt = jwt.substring(7);
        
        Claims claims = Jwts.parser()
                .verifyWith(key).build().parseSignedClaims(jwt).getPayload();
        
        String email = String.valueOf(claims.get("email"));
        
        return email;
    }
    
}
