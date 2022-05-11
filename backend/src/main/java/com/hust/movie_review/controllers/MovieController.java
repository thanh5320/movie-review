package com.hust.movie_review.controllers;

import com.hust.movie_review.common.data.response.DfResponse;
import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.service.template.IMovieService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/movie")
public class MovieController {
    IMovieService movieService;

    public MovieController(IMovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/listing")
    public DfResponse<List<Movie>> listing(){
        List<Movie> movies = movieService.getMovies();
        return DfResponse.okEntity(movies);
    }

    @PostMapping("store")
    public DfResponse<Movie> store(@RequestBody @Valid StoreRequest request){
        this.movieService.insert(request);
        return DfResponse.okEntity();
    }
}
