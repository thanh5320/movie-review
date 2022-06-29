package com.hust.movie_review.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class CategoryResponse {
    private int id;

    private String name;

    private String description;

    private Set<MovieResponse> movies;

    private Date createdAt;

    private Date updateAt;
}
