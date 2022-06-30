package com.hust.movie_review.data.request.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class RegisterRequest {
    @NotEmpty(message = "username is not empty!")
    private String fullName;

    @NotEmpty(message = "username is not empty!")
    private String username;

    @NotEmpty(message = "password is not empty!")
    private String password;

    @Email(message = "Email not validate")
    private String email;

    @NotEmpty(message = "Phone number not validate")
    private String phoneNumber;
}
