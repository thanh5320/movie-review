package com.hust.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
