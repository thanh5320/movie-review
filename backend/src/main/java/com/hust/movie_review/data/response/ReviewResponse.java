package com.hust.movie_review.data.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.models.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ReviewResponse {
    private int id;

    public UserResponse user;

    private MovieResponse movie;

    private double rating;

    private Date createdAt;

    private Date updateAt;
}
