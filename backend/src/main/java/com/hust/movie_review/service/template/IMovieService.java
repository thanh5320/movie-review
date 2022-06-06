package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.models.Movie;

import java.util.List;

public interface IMovieService extends  IService<Movie>{
    List<Movie> getTopMovie(int top);
    Movie insert(StoreRequest movie);

    void update(int id, Movie movie);
}
