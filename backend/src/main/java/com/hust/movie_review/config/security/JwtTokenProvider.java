package com.hust.movie_review.config.security;

import com.hust.movie_review.common.Constants;
import com.hust.movie_review.models.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Generate token from user's information
     */
    public String generateToken(CustomUserDetails customUserDetails){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder().setSubject(Long.toString(customUserDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Get user's information from token
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            // write log: token invalid
        } catch (ExpiredJwtException ex) {
            // write log: token expired
        } catch (UnsupportedJwtException ex) {
            // write log: jwt unsupported
        } catch (IllegalArgumentException ex) {
            // write log: string is empty
        }
        return false;
    }
}
