package com.example.NewsAggregatorAPI.configuration;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.NewsAggregatorAPI.util.JwtUtility;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authenticationHeader = request.getHeader("authorization");

        if(authenticationHeader == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing Authorization header");
        }

        if(!JwtUtility.validateToken(authenticationHeader)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid user token");
            return;


        }

        Claims claim = JwtUtility.getClaims(authenticationHeader);
        
        String username = Claims.getSubject();
        String role = Claims.get("roles", String.class);
        filterChain.doFilter(request, response);
        
    }

    protected  boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("register") || path.contains("signin") || path.contains("verifyRegistertion");
    }
    
}
