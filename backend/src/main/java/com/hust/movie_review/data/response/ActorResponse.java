package com.hust.movie_review.data.response;

import com.hust.movie_review.models.Country;
import com.hust.movie_review.models.Movie;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ActorResponse {
    private int id;

    private String fullName;

    private CountryResponse country;

    private int yearBirthday;

    private String gender;

    Set<MovieResponse> movies;

    private Date createdAt;

    private Date updateAt;
}
