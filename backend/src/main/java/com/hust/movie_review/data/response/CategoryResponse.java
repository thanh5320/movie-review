package com.hust.movie_review.data.response;

import com.hust.movie_review.models.Movie;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.OneToMany;
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
