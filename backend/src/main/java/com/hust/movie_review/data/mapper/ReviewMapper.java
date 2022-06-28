package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.ReviewResponse;
import com.hust.movie_review.data.response.UserResponse;
import com.hust.movie_review.models.Review;
import com.hust.movie_review.models.Role;
import com.hust.movie_review.models.User;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.CommentMapper.toCommentResponsesSimple;
import static com.hust.movie_review.data.mapper.MovieMapper.toMovieResponseSimple;
import static com.hust.movie_review.data.mapper.UserMapper.toUserResponseSimple;

public class ReviewMapper {

    public static ReviewResponse toReviewResponse(Review review){
        return toReviewResponseSimple(review)
                .setMovie(toMovieResponseSimple(review.getMovie()))
                .setUser(toUserResponseSimple(review.getUser()));
    }

    public static List<ReviewResponse> toReviewResponses(List<Review> reviews){
        return Optional.ofNullable(reviews).orElse(new ArrayList<>())
                .stream()
                .map(ReviewMapper::toReviewResponse)
                .collect(Collectors.toList());
    }

    public static Set<ReviewResponse> toReviewResponses(Set<Review> reviews){
        return Optional.ofNullable(reviews).orElse(new HashSet<>())
                .stream()
                .map(ReviewMapper::toReviewResponse)
                .collect(Collectors.toSet());
    }
    public static ReviewResponse toReviewResponseSimple(Review review){
        return new ReviewResponse()
                .setId(review.getId())
                .setRating(review.getRating())
                .setCreatedAt(review.getCreatedAt())
                .setUpdateAt(review.getUpdateAt());
    }

    public static List<ReviewResponse> toReviewResponsesSimple(List<Review> reviews){
        return Optional.ofNullable(reviews).orElse(new ArrayList<>())
                .stream()
                .map(ReviewMapper::toReviewResponseSimple)
                .collect(Collectors.toList());
    }

    public static Set<ReviewResponse> toReviewResponsesSimple(Set<Review> reviews){
        return Optional.ofNullable(reviews).orElse(new HashSet<>())
                .stream()
                .map(ReviewMapper::toReviewResponseSimple)
                .collect(Collectors.toSet());
    }
}
