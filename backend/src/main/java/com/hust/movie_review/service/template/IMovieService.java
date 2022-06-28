package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.data.response.MovieResponse;
import com.hust.movie_review.models.Movie;

import java.util.List;

public interface IMovieService extends  IService<Movie>{
    List<Movie> getTopMovie(int top);
    List<Movie> getTopMovieByType(int top, String type);
    List<Movie> getTopMovieByType(int page, int pageSize, String type, String sortColumn);
    int countByType(String type);
    Movie insert(StoreRequest movie);
    List<Movie> search(int page, int pageSize, String sortColumn, String search);
    int countSearch(String search);
    void update(int id, Movie movie);
}
