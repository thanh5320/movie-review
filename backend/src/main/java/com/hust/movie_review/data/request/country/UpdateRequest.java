package com.hust.movie_review.data.request.country;

import com.hust.movie_review.models.Actor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UpdateRequest {
    private String code;

    private String name;

    private String continent;

    private Set<Actor> actors;

    private Date createdAt;

    private Date updateAt;
}
