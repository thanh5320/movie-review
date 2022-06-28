package com.hust.movie_review.data.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hust.movie_review.models.Actor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
public class CountryResponse {
    private String code;

    private String name;

    private String continent;

    private Set<ActorResponse> actors;

    private Date createdAt;

    private Date updateAt;
}
