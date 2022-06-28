package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.CategoryResponse;
import com.hust.movie_review.data.response.CommentResponse;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.models.Comment;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.MovieMapper.toMovieResponseSimple;
import static com.hust.movie_review.data.mapper.MovieMapper.toMovieResponsesSimple;
import static com.hust.movie_review.data.mapper.UserMapper.toUserResponseSimple;

public class CategoryMapper {
    public static CategoryResponse toCategoryResponse(Category comment){
        return toCategoryResponseSimple(comment)
                .setMovies(toMovieResponsesSimple(comment.getMovies()));
    }

    public static List<CategoryResponse> toCategoryResponses(List<Category> categories){
        return Optional.ofNullable(categories).orElse(new ArrayList<>())
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    public static Set<CategoryResponse> toCategoryResponses(Set<Category> categories){
        return Optional.ofNullable(categories).orElse(new HashSet<>())
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .collect(Collectors.toSet());
    }

    public static CategoryResponse toCategoryResponseSimple(Category category){
        return new CategoryResponse()
                .setId(category.getId())
                .setName(category.getName())
                .setDescription(category.getDescription())
                .setCreatedAt(category.getCreatedAt())
                .setUpdateAt(category.getUpdateAt());

    }

    public static List<CategoryResponse> toCategoryResponsesSimple(List<Category> categories){
        return Optional.ofNullable(categories).orElse(new ArrayList<>())
                .stream()
                .map(CategoryMapper::toCategoryResponseSimple)
                .collect(Collectors.toList());
    }


    public static Set<CategoryResponse> toCategoryResponsesSimple(Set<Category> categories){
        return Optional.ofNullable(categories).orElse(new HashSet<>())
                .stream()
                .map(CategoryMapper::toCategoryResponseSimple)
                .collect(Collectors.toSet());
    }
}
