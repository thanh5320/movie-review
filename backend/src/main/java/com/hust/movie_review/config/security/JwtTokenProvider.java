package com.hust.movie_review.config.security;

import com.hust.movie_review.models.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String JWT_SECRET = "1234567123";
    private final long JWT_EXPIRATION = 604800000L;

    /**
     * Generate token from user's information
     */
    public String generateToken(CustomUserDetails customUserDetails){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder().setSubject(Long.toString(customUserDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    /**
     * Get user's information from token
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
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
