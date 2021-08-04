package com.example.mockserver.securityConfig;

import com.example.mockserver.dao.ExceptionDAO;
import com.example.mockserver.error.CustomException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.mockserver.utils.Utils.convertErrorToJson;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;
    @Value("${jwt.secret}")
    private String KEY;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!header.startsWith("Bearer ")) {
            ExceptionDAO exception = new ExceptionDAO("Request is invalid.", "01.04.02");
            response.getWriter().write(convertErrorToJson(exception));
        }
        final String token = header.split(" ")[1].trim();
        try {
            validateToken(token);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            ExceptionDAO exception = new ExceptionDAO(ex.getMessage(), " 01.04.01 ");
            response.getWriter().write(convertErrorToJson(exception));
        }
        filterChain.doFilter(request, response);
    }


    private void validateToken(String token) throws CustomException {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        } catch (MalformedJwtException ex) {
            logger.error(ex.getMessage(), ex);
            throw new CustomException("Request is invalid.", "01.04.02");
        } catch (SignatureException ex) {
            logger.error(ex.getMessage(), ex);
            throw new CustomException("Signature is corrupted or wrong.", "01.04.03");
        } catch (ExpiredJwtException ex) {
            logger.error(ex.getMessage(), ex);
            throw new CustomException("The token has expired.", "01.04.04");
        } catch (UnsupportedJwtException ex) {
            logger.error(ex.getMessage(), ex);
            throw new CustomException("The provided token is not supported.", "01.04.05");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CustomException("No further information.", "01.04.01");
        }

    }
}

