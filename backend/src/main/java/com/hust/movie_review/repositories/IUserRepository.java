package com.hust.movie_review.repositories;

import com.hust.movie_review.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    User findByEmail(String email);
    //User save();
}
