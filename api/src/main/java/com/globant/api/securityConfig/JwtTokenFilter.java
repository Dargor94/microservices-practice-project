package com.globant.api.securityConfig;

import com.globant.api.dao.ExceptionHandlerDAO;
import com.globant.api.error.ExceptionHandler;
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

import static com.globant.api.utils.Utils.convertObjectToJson;


public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;
    @Value("${jwt.secret}")
    private String KEY;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!header.startsWith("Bearer ")) {
            ExceptionHandlerDAO exception = new ExceptionHandlerDAO("400", "Malformed token.", "BAD REQUEST");
            response.getWriter().write(convertObjectToJson(exception));
        }
        final String token = header.split(" ")[1].trim();
        try {
            validateToken(token);
            filterChain.doFilter(request, response);
        } catch (ExceptionHandler ex) {
            logger.error(ex.getMessage(), ex);
            response.setStatus(ex.getStatus().value());
            ExceptionHandlerDAO exception = new ExceptionHandlerDAO(ex.getMessage(), ex.getCode(), ex.getStatus().getReasonPhrase());
            response.getWriter().write(convertObjectToJson(exception));
        }
    }


    private void validateToken(String token) throws ExceptionHandler {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            //jws.getBody().setExpiration(Date.from(Instant.now().plusMillis(1000)));
        } catch (SignatureException ex) {
            throw new ExceptionHandler("Signature is corrupted or wrong.", "401", HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException ex) {
            throw new ExceptionHandler("Malformed token.", "400", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException ex) {
            throw new ExceptionHandler("This token has expired.", "403", HttpStatus.FORBIDDEN);
        } catch (UnsupportedJwtException ex) {
            throw new ExceptionHandler("The provided token is not supported.", "400", HttpStatus.BAD_REQUEST);
       /* } catch (IllegalArgumentException ex) {
            throw new ExceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR.");*/ //SAME AS BELOW
        } catch (Exception ex) {
            throw new ExceptionHandler("INTERNAL SERVER ERROR.", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

