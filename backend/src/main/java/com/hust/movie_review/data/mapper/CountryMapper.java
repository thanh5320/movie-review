package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.CountryResponse;
import com.hust.movie_review.data.response.MovieResponse;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.models.Movie;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.ActorMapper.toActorResponsesSimple;
import static com.hust.movie_review.data.mapper.CategoryMapper.toCategoryResponseSimple;
import static com.hust.movie_review.data.mapper.CommentMapper.toCommentResponsesSimple;
import static com.hust.movie_review.data.mapper.ReviewMapper.toReviewResponsesSimple;

public class CountryMapper {

    public static CountryResponse toCountryResponse(Country movie){
        return toCountryResponseSimple(movie)
                .setActors(toActorResponsesSimple(movie.getActors()));
    }

    public static List<CountryResponse> toCountryResponses(List<Country> countries){
        return Optional.ofNullable(countries).orElse(new ArrayList<>())
                .stream()
                .map(CountryMapper::toCountryResponse)
                .collect(Collectors.toList());
    }

    public static Set<CountryResponse> toCountryResponses(Set<Country> countries){
        return Optional.ofNullable(countries).orElse(new HashSet<>())
                .stream()
                .map(CountryMapper::toCountryResponse)
                .collect(Collectors.toSet());
    }
    public static CountryResponse toCountryResponseSimple(Country country){
        return new CountryResponse()
                .setCode(country.getCode())
                .setName(country.getName())
                .setContinent(country.getContinent())
                .setCreatedAt(country.getCreatedAt())
                .setUpdateAt(country.getUpdateAt());

    }

    public static List<CountryResponse> toCountryResponsesSimple(List<Country> countries){
        return Optional.ofNullable(countries).orElse(new ArrayList<>())
                .stream()
                .map(CountryMapper::toCountryResponseSimple)
                .collect(Collectors.toList());
    }


    public static Set<CountryResponse> toCountryResponsesSimple(Set<Country> countries){
        return Optional.ofNullable(countries).orElse(new HashSet<>())
                .stream()
                .map(CountryMapper::toCountryResponseSimple)
                .collect(Collectors.toSet());
    }
}
