package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.CommentResponse;
import com.hust.movie_review.data.response.CountryResponse;
import com.hust.movie_review.data.response.MovieResponse;
import com.hust.movie_review.models.Comment;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.models.Movie;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.ActorMapper.toActorResponsesSimple;
import static com.hust.movie_review.data.mapper.CategoryMapper.toCategoryResponseSimple;
import static com.hust.movie_review.data.mapper.CountryMapper.toCountryResponseSimple;
import static com.hust.movie_review.data.mapper.MovieMapper.toMovieResponseSimple;
import static com.hust.movie_review.data.mapper.ReviewMapper.toReviewResponsesSimple;
import static com.hust.movie_review.data.mapper.UserMapper.toUserResponseSimple;

public class CommentMapper {

    public static CommentResponse toCommentResponse(Comment comment){
        return toCommentResponseSimple(comment)
                .setUser(toUserResponseSimple(comment.getUser()))
                .setMovie(toMovieResponseSimple(comment.getMovie()));
    }

    public static List<CommentResponse> toCommentResponses(List<Comment> comments){
        return Optional.ofNullable(comments).orElse(new ArrayList<>())
                .stream()
                .map(CommentMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    public static Set<CommentResponse> toCommentResponses(Set<Comment> comments){
        return Optional.ofNullable(comments).orElse(new HashSet<>())
                .stream()
                .map(CommentMapper::toCommentResponse)
                .collect(Collectors.toSet());
    }
    public static CommentResponse toCommentResponseSimple(Comment comment){
        return new CommentResponse()
                .setId(comment.getId())
                .setComment(comment.getComment())
                .setCreatedAt(comment.getCreatedAt())
                .setUpdateAt(comment.getUpdateAt());

    }

    public static List<CommentResponse> toCommentResponsesSimple(List<Comment> comments){
        return Optional.ofNullable(comments).orElse(new ArrayList<>())
                .stream()
                .map(CommentMapper::toCommentResponseSimple)
                .collect(Collectors.toList());
    }


    public static Set<CommentResponse> toCommentResponsesSimple(Set<Comment> comments){
        return Optional.ofNullable(comments).orElse(new HashSet<>())
                .stream()
                .map(CommentMapper::toCommentResponseSimple)
                .collect(Collectors.toSet());
    }
}
