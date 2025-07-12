package com.example.NewsAggregatorAPI.util;

import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class JwtUtility {

    private static Key secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public static String generateToken(String username, String role) {
       return Jwts.builder()
       .subject(username)
       .setIssuedAt(new Date())
       .setExpiration(new Date(System.currentTimeMillis() + 8 * 60 * 60 *1000))
       .claim("roles", "ROLE" + role)
       .signWith(secretKey)
       .compact();
    }

    public static boolean validateToken(String authenticationHeader) {
        try{   
        Claims body = Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(authenticationHeader).getPayload();
         }catch(SignatureException exception){
            System.err.println("I");
         } return true;
    }

    public static Claims getClaims(String authenticationHeader) {
        try {
            return Jwts.parser().setSigningKey(secretkey).build().parseClaimsJws(authenticationHeader).getBody
        } catch ( e) {
        }
    }
    
}