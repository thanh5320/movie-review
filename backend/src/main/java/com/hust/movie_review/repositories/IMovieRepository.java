package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findMoviesByType(Pageable pageable, String type);
    int countByType(String type);

    Page<Movie> findByTitleContains(Pageable pageable, String search);
    int countByTitleContains(String search);
}
