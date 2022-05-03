package com.hust.movie_review.data.mapper.user;

import com.hust.movie_review.data.request.user.CreateUserRequest;
import com.hust.movie_review.data.response.user.UserInfoResponse;
import com.hust.movie_review.models.User;
import lombok.SneakyThrows;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Mapping(target = "roles", ignore = true)
    public abstract User createUserToUser(CreateUserRequest source);

    @SneakyThrows
    @AfterMapping
    protected void afterCreateUserToUser(@MappingTarget User target, CreateUserRequest source){
        target.setStatus(true);
        target.setPassword(passwordEncoder.encode(source.getPassword()));
    }

    @Mapping(target = "roles", ignore = true)
    public abstract UserInfoResponse UserToUserInfo(User user);
}
