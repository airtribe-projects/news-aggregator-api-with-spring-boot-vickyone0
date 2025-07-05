package com.example.NewsAggregatorAPI.util;

import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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
    
}