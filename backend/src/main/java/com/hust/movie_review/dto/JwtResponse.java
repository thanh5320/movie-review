package com.hust.movie_review.dto;

import com.hust.movie_review.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
public class JwtResponse {
    private String token;
    private String type;
    private User user;
    private Set<String> roles;
}
