package com.hust.movie_review.controllers;

import com.hust.movie_review.data.mapper.MovieMapper;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.data.response.DfResponseList;
import com.hust.movie_review.data.response.MovieResponse;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.service.template.IMovieService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/movie")
public class MovieController {
    IMovieService movieService;

    public MovieController(IMovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/listing")
    public DfResponse<List<MovieResponse>> listing(){
        List<MovieResponse> movies = MovieMapper.toMovieResponses(movieService.listing());
        return DfResponse.okEntity(movies);
    }

    @GetMapping("/detail/{id}")
    public DfResponse<MovieResponse> detail(@PathVariable int id){
        MovieResponse movie = MovieMapper.toMovieResponse(movieService.detail(id));
        return DfResponse.okEntity(movie);
    }

    @GetMapping("top/{top}")
    public DfResponse<List<MovieResponse>> getTop(
            @RequestParam String type,
            @PathVariable int top
    ){
        List<Movie> topMovie = null;
        if(Objects.equals(type, "movie")){
            topMovie = movieService.getTopMovie(top);
        } else {
            topMovie = movieService.getTopMovieByType(top, type);
        }

        return DfResponse.okEntity(MovieMapper.toMovieResponses(topMovie));
    }

    @GetMapping("upcoming")
    public DfResponseList<MovieResponse> getUpcoming(
            @RequestParam String type,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize
    ){
        List<Movie> topMovie = movieService.getTopMovieByType(page-1, pageSize, type, "createdAt");
        int total = movieService.countByType(type);
        return DfResponseList.okEntity(MovieMapper.toMovieResponses(topMovie), total);
    }

    @GetMapping("search")
    public DfResponseList<MovieResponse> search(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
            @RequestParam String search
    ){
        List<Movie> topMovie = movieService.search(page, pageSize, "createdAt", search);
        int total = movieService.countSearch(search);
        return DfResponseList.okEntity(MovieMapper.toMovieResponses(topMovie), total);
    }

    @PostMapping("store")
    public DfResponse<MovieResponse> store(@RequestBody @Valid StoreRequest request){
        Movie movie = this.movieService.insert(request);
        return DfResponse.okEntity(MovieMapper.toMovieResponse(movie));
    }
}
