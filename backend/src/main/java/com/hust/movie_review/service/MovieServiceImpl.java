package com.hust.movie_review.service;

import com.hust.movie_review.common.OffsetBasedPageable;
import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.repositories.CountryRepository;
import com.hust.movie_review.repositories.MovieRepository;
import com.hust.movie_review.service.template.IMovieService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements IMovieService {
    MovieRepository movieRepository;
    CountryRepository countryRepository;

    public MovieServiceImpl(MovieRepository movieRepository, CountryRepository countryRepository){
        this.movieRepository = movieRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return new ArrayList<>(movieRepository.findAll());
    }

    @Override
    public List<Movie> getTop10Movies(){
        Pageable pageable = new OffsetBasedPageable(0, 10);
        return movieRepository.findAll(pageable).get().collect(Collectors.toList());
    }

    @Override
    public Movie detail(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    @Override
    public Movie insert(StoreRequest request) {
        Movie movie = new Movie();

        Country country = countryRepository.findByCode(request.getCountry());

        movie.setDirector(request.getDirector());
        movie.setCountry(country);
        movie.setTrailer(request.getTrailer());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setDescription(request.getDescription());
        movie.setYear(request.getYear());

        return movieRepository.save(movie);
    }

    @Override
    public void update(int id, Movie movie) {
        movie.setId(id);
        movieRepository.save(movie);
    }

    @Override
    public void delete(int id)
    {
        movieRepository.deleteById(id);
    }
}
