package com.hust.movie_review.controllers;

import com.hust.movie_review.data.mapper.UserMapper;
import com.hust.movie_review.data.request.user.UpdateUserRequest;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.data.request.user.CreateUserRequest;
import com.hust.movie_review.data.response.UserResponse;
import com.hust.movie_review.data.response.user.UserInfoResponse;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.service.template.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/admin/user/")
public class AdminController {
    private final IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("listing")
    public DfResponse<List<UserResponse>> listing(){
        return DfResponse.okEntity(UserMapper.toUserResponses(userService.listing()));
    }

    @PostMapping("create")
    public DfResponse<UserInfoResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        return DfResponse.okEntity(userService.createUser(request));
    }

    @PutMapping(value = "/update")
    public DfResponse<UserInfoResponse> update(@RequestBody @Valid UpdateUserRequest request){
        return DfResponse.okEntity(userService.updateUser(request));
    }

    @DeleteMapping(value = "/delete/{id}")
    public DfResponse<String> delete(@PathVariable Integer id){
        return DfResponse.okEntity(userService.deleteUser(id));
    }
}
