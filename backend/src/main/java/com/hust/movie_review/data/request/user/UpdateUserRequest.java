package com.hust.movie_review.data.request.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UpdateUserRequest {
    @NotNull(message = "userId is not empty")
    private int id;
    private String username;
    private String password;
    @Email(message = "Email invalidate")
    private String email;
    private String fullName;
    private String phoneNumber;
}
