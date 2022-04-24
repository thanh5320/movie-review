package com.hust.movie_review.dto;

import com.hust.movie_review.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private User user;
    private Set<String> roles;
}
