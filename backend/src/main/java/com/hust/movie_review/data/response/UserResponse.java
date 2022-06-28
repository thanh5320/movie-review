package com.hust.movie_review.data.response;

import com.hust.movie_review.models.Comment;
import com.hust.movie_review.models.Review;
import com.hust.movie_review.models.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserResponse {
    private int id;

    private String username;

    private String phoneNumber;

    private String email;

    private String fullName;

    private boolean status;

    private Set<CommentResponse> comments;

    private Set<ReviewResponse> reviews;

    Set<String> roles;

    private Date createdAt;

    private Date updateAt;
}
