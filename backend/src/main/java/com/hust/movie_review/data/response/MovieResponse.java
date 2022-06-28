package com.hust.movie_review.data.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hust.movie_review.models.*;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class MovieResponse {
    private int id;

    private String title;

    private CategoryResponse category;

    private CountryResponse country;

    private String director;

    private int year;

    private double rating;

    private String description;

    private int releaseYear;

    private String trailer;

    private String thumbnail;

    private Date createdAt;

    private Date updateAt;

    private String type;

    Set<ActorResponse> actors;

    private Set<CommentResponse> comments;

    private Set<ReviewResponse> reviews;
}
