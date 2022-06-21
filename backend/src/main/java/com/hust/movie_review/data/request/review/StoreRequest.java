package com.hust.movie_review.data.request.review;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class StoreRequest {
    @NotNull(message = "rating not empty")
    private int rating;

    @NotNull(message = "rating not empty")
    private int movieId;

    @NotNull(message = "rating not empty")
    private int userId;
}
