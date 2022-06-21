package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
}
