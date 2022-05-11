package com.hust.movie_review.service.template;

import com.hust.movie_review.models.Movie;

import java.util.List;

public interface IService<T> {
    List<T> listing();

    T detail(int id);

    boolean delete(int id);
}
