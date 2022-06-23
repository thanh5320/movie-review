package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
}
