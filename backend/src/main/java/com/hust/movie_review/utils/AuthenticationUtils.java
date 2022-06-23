package com.hust.movie_review.utils;

import com.hust.movie_review.models.UserDetailsImpl;
import org.springframework.security.core.Authentication;

import static org.apache.commons.lang3.math.NumberUtils.createInteger;

public class AuthenticationUtils {
    public static String getUsername(Authentication authentication) {
        if (authentication == null) return null;
        return ((UserDetailsImpl)authentication.getPrincipal()).getUsername();
    }
}
