package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
