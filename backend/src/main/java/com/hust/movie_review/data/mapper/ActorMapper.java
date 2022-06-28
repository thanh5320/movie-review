package com.hust.movie_review.data.mapper;

import com.hust.movie_review.data.response.ActorResponse;
import com.hust.movie_review.data.response.CategoryResponse;
import com.hust.movie_review.models.Actor;
import com.hust.movie_review.models.Category;

import java.util.*;
import java.util.stream.Collectors;

import static com.hust.movie_review.data.mapper.CountryMapper.toCountryResponseSimple;
import static com.hust.movie_review.data.mapper.MovieMapper.toMovieResponsesSimple;

public class ActorMapper {
    public static ActorResponse toActorResponse(Actor actor){
        return toActorResponseSimple(actor)
                .setMovies(toMovieResponsesSimple(actor.getMovies()))
                .setCountry(toCountryResponseSimple(actor.getCountry()));
    }

    public static List<ActorResponse> toActorResponses(List<Actor> categories){
        return Optional.ofNullable(categories).orElse(new ArrayList<>())
                .stream()
                .map(ActorMapper::toActorResponse)
                .collect(Collectors.toList());
    }

    public static Set<ActorResponse> toActorResponses(Set<Actor> categories){
        return Optional.ofNullable(categories).orElse(new HashSet<>())
                .stream()
                .map(ActorMapper::toActorResponse)
                .collect(Collectors.toSet());
    }
    public static ActorResponse toActorResponseSimple(Actor actor){
        return new ActorResponse()
                .setId(actor.getId())
                .setFullName(actor.getFullName())
                .setYearBirthday(actor.getYearBirthday())
                .setGender(actor.getGender())
                .setCreatedAt(actor.getCreatedAt())
                .setUpdateAt(actor.getUpdateAt());

    }

    public static List<ActorResponse> toActorResponsesSimple(List<Actor> categories){
        return Optional.ofNullable(categories).orElse(new ArrayList<>())
                .stream()
                .map(ActorMapper::toActorResponseSimple)
                .collect(Collectors.toList());
    }


    public static Set<ActorResponse> toActorResponsesSimple(Set<Actor> categories){
        return Optional.ofNullable(categories).orElse(new HashSet<>())
                .stream()
                .map(ActorMapper::toActorResponseSimple)
                .collect(Collectors.toSet());
    }
}
