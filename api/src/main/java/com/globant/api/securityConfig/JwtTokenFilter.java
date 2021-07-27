package com.globant.api.securityConfig;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String KEY;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header.isEmpty() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            throw new ServletException("Empty body.");
        }
        final String token = header.split(" ")[1].trim();
        try {
            validateToken(token);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (SignatureException ex) {
            throw new SignatureException("Invalid JWT signature trace: %s", ex);
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid JWT token trace: %s", ex);
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtException(null, null, "Expired JWT token trace: %s", ex);
        } catch (UnsupportedJwtException ex) {
            throw new UnsupportedJwtException(String.format("Unsupported JWT token trace: %s", ex.getMessage()), ex);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("JWT token compact of handler are invalid trace: %s", ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }
}

