package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.UserResponse;
import com.hust.movie_review.models.Role;
import com.hust.movie_review.models.User;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.CommentMapper.toCommentResponses;
import static com.hust.movie_review.data.mapper.ReviewMapper.toReviewResponses;

public class UserMapper {
    public static UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setFullName(user.getFullName())
                .setStatus(user.isStatus())
                .setCreatedAt(user.getCreatedAt())
                .setUpdateAt(user.getUpdateAt())
                .setComments(toCommentResponses(user.getComments()))
                .setReviews(toReviewResponses(user.getReviews()))
                .setRoles(Optional.ofNullable(user.getRoles()).orElse(new HashSet<>())
                        .stream()
                        .map(Role::getName).collect(Collectors.toSet()));

        return userResponse;
    }

   public static List<UserResponse> toUserResponses(List<User> users){
       return Optional.ofNullable(users).orElse(new ArrayList<>())
                .stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public static Set<UserResponse> toUserResponses(Set<User> users){
        return Optional.ofNullable(users).orElse(new HashSet<>())
                .stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toSet());
    }

    public static UserResponse toUserResponseSimple(User user){
        UserResponse userResponse = new UserResponse()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setFullName(user.getFullName())
                .setStatus(user.isStatus())
                .setCreatedAt(user.getCreatedAt())
                .setUpdateAt(user.getUpdateAt())
                .setRoles(Optional.ofNullable(user.getRoles()).orElse(new HashSet<>())
                        .stream()
                        .map(Role::getName).collect(Collectors.toSet()));

        return userResponse;
    }

    public static List<UserResponse> toUserResponsesSimple(List<User> users){
        return Optional.ofNullable(users).orElse(new ArrayList<>())
                .stream()
                .map(UserMapper::toUserResponseSimple)
                .collect(Collectors.toList());
    }

    public static Set<UserResponse> toUserResponsesSimple(Set<User> users){
        return Optional.ofNullable(users).orElse(new HashSet<>())
                .stream()
                .map(UserMapper::toUserResponseSimple)
                .collect(Collectors.toSet());
    }
}
