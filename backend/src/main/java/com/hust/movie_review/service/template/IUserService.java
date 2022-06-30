package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.user.CreateUserRequest;
import com.hust.movie_review.data.request.user.RegisterRequest;
import com.hust.movie_review.data.request.user.UpdateUserRequest;
import com.hust.movie_review.data.response.user.LoginResponse;
import com.hust.movie_review.data.response.user.UserInfoResponse;
import com.hust.movie_review.data.request.LoginRequest;
import com.hust.movie_review.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    UserInfoResponse createUser(CreateUserRequest request);
    LoginResponse login(LoginRequest request, AuthenticationManager authenticationManager);
    User loadUserById(int id);
    User getCurrentUserByUsername(String username);
    UserInfoResponse updateUser(UpdateUserRequest request);

    String deleteUser(int id);

    List<User> listing();

    UserInfoResponse registerUser(RegisterRequest request);
}
