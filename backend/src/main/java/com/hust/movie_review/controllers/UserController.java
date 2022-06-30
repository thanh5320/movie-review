package com.hust.movie_review.controllers;

import com.hust.movie_review.data.mapper.UserMapper;
import com.hust.movie_review.data.request.user.RegisterRequest;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.data.response.UserResponse;
import com.hust.movie_review.data.response.user.UserInfoResponse;
import com.hust.movie_review.models.User;
import com.hust.movie_review.service.template.IUserService;
import com.hust.movie_review.utils.AuthenticationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public DfResponse<UserResponse> me(Authentication authentication){
        String username = AuthenticationUtils.getUsername(authentication);
        User user = userService.getCurrentUserByUsername(username);

        return DfResponse.okEntity(UserMapper.toUserResponse(user));
    }

    @PostMapping("/register")
    public DfResponse<UserInfoResponse> register(@RequestBody @Valid RegisterRequest request){
        return DfResponse.okEntity(userService.registerUser(request));
    }
}
