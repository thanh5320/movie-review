package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<Actor, Integer> {
}
