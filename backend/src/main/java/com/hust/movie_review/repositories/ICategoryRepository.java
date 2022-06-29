package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
