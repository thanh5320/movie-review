package com.hust.movie_review.controllers;

import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.service.template.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movie")
public class MovieController {
    IMovieService movieService;

    public MovieController(IMovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/listing")
    public DfResponse<List<Movie>> listing(){
        List<Movie> movies = movieService.listing();
        return DfResponse.okEntity(movies);
    }

    @GetMapping("/detail/{id}")
    public DfResponse<Movie> detail(@PathVariable int id){
        Movie movie = movieService.detail(id);
        return DfResponse.okEntity(movie);
    }

    @GetMapping("top/{top}")
    public DfResponse<List<Movie>> getTop(@PathVariable int top){
        List<Movie> topMovie = movieService.getTopMovie(top);
        return DfResponse.okEntity(topMovie);
    }

    @PostMapping("store")
    public DfResponse<Movie> store(@RequestBody @Valid StoreRequest request){
        this.movieService.insert(request);
        return DfResponse.okEntity();
    }
}
