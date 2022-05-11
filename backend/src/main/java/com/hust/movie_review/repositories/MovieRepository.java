package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
