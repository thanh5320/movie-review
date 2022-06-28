package com.hust.movie_review.controllers;

import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.data.response.UserResponse;
import com.hust.movie_review.service.template.IUserService;
import com.hust.movie_review.utils.AuthenticationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        UserResponse user = userService.getCurrentUserByUsername(username);
        return DfResponse.okEntity(user);
    }
}
