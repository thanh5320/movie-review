package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByUserId(int userId);
}
