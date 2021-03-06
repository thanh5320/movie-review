package com.hust.movie_review.repositories;

import com.hust.movie_review.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepository extends JpaRepository<Country, String> {
    Country findByCode(String code);
}
