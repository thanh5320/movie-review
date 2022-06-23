package com.hust.movie_review.data.request.comment;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class StoreRequest {
    @NotNull(message = "user_id not null")
    int userId;

    @NotNull(message = "movie id not null")
    int movieId;

    @NotNull(message = "comment not null")
    String comment;
}
