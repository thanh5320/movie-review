package com.hust.movie_review.data.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hust.movie_review.models.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class RoleResponse {
    private int id;

    private String name;

    private String displayName;

    Set<UserResponse> users;

    private Date createdAt;

    private Date updateAt;
}
