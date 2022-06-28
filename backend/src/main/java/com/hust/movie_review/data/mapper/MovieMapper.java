package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.MovieResponse;
import com.hust.movie_review.data.response.ReviewResponse;
import com.hust.movie_review.data.response.UserResponse;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.models.Review;
import com.hust.movie_review.models.Role;
import com.hust.movie_review.models.User;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.ActorMapper.toActorResponseSimple;
import static com.hust.movie_review.data.mapper.ActorMapper.toActorResponsesSimple;
import static com.hust.movie_review.data.mapper.CategoryMapper.toCategoryResponseSimple;
import static com.hust.movie_review.data.mapper.CommentMapper.toCommentResponses;
import static com.hust.movie_review.data.mapper.CommentMapper.toCommentResponsesSimple;
import static com.hust.movie_review.data.mapper.CountryMapper.toCountryResponseSimple;
import static com.hust.movie_review.data.mapper.ReviewMapper.toReviewResponses;
import static com.hust.movie_review.data.mapper.ReviewMapper.toReviewResponsesSimple;
import static com.hust.movie_review.data.mapper.UserMapper.toUserResponseSimple;

public class MovieMapper {


    public static MovieResponse toMovieResponse(Movie movie){
        return toMovieResponseSimple(movie)
                .setCategory(toCategoryResponseSimple(movie.getCategory()))
                .setCountry(toCountryResponseSimple(movie.getCountry()))
                .setActors(toActorResponsesSimple(movie.getActors()))
                .setComments(toCommentResponses(movie.getComments()))
                .setReviews(toReviewResponses(movie.getReviews()));
    }

    public static List<MovieResponse> toMovieResponses(List<Movie> movies){
        return Optional.ofNullable(movies).orElse(new ArrayList<>())
                .stream()
                .map(MovieMapper::toMovieResponse)
                .collect(Collectors.toList());
    }

    public static Set<MovieResponse> toMovieResponses(Set<Movie> movies){
        return Optional.ofNullable(movies).orElse(new HashSet<>())
                .stream()
                .map(MovieMapper::toMovieResponse)
                .collect(Collectors.toSet());
    }
    public static MovieResponse toMovieResponseSimple(Movie movie){
        return new MovieResponse()
                .setId(movie.getId())
                .setTitle(movie.getTitle())
                .setDirector(movie.getDirector())
                .setYear(movie.getYear())
                .setRating(movie.getRating())
                .setDescription(movie.getDescription())
                .setCreatedAt(movie.getCreatedAt())
                .setReleaseYear(movie.getReleaseYear())
                .setTrailer(movie.getTrailer())
                .setType(movie.getType())
                .setThumbnail(movie.getThumbnail())
                .setUpdateAt(movie.getUpdateAt());

    }

    public static List<MovieResponse> toMovieResponsesSimple(List<Movie> movies){
        return Optional.ofNullable(movies).orElse(new ArrayList<>())
                .stream()
                .map(MovieMapper::toMovieResponseSimple)
                .collect(Collectors.toList());
    }


    public static Set<MovieResponse> toMovieResponsesSimple(Set<Movie> movies){
        return Optional.ofNullable(movies).orElse(new HashSet<>())
                .stream()
                .map(MovieMapper::toMovieResponseSimple)
                .collect(Collectors.toSet());
    }

}
