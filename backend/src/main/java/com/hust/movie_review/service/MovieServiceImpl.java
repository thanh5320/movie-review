package com.hust.movie_review.service;

import com.hust.movie_review.common.OffsetBasedPageable;
import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.repositories.CountryRepository;
import com.hust.movie_review.repositories.IMovieRepository;
import com.hust.movie_review.service.template.IMovieService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl extends BaseService<Movie> implements IMovieService {
    IMovieRepository movieRepository;
    CountryRepository countryRepository;

    public MovieServiceImpl(IMovieRepository movieRepository, CountryRepository countryRepository){
        super(movieRepository);
        this.movieRepository = movieRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Movie> getTopMovie(int top){
        Pageable pageable = new OffsetBasedPageable(0, top, "rating");
        return this.listing(pageable);
    }

    @Override
    public List<Movie> getTopMovieByType(int top, String type){
        Pageable pageable = new OffsetBasedPageable(0, top, "rating");
        return movieRepository.findMoviesByType(pageable, type).get().collect(Collectors.toList());
    }

    @Override
    public List<Movie> getTopMovieByType(int page, int pageSize, String type, String sortColumn){
        Pageable pageable = new OffsetBasedPageable(page, pageSize, sortColumn);
        return movieRepository.findMoviesByType(pageable, type).get().collect(Collectors.toList());
    }

    @Override
    public int countByType(String type){
        return movieRepository.countByType(type);
    }

    @Override
    public Movie insert(StoreRequest request) {
        Movie movie = new Movie();

        Country country = countryRepository.findByCode(request.getCountry());

        movie.setDirector(request.getDirector())
                .setCountry(country)
                .setTrailer(request.getTrailer())
                .setReleaseYear(request.getReleaseYear())
                .setDescription(request.getDescription())
                .setYear(request.getYear());

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> search(int page, int pageSize, String sortColumn, String search) {
        Pageable pageable = new OffsetBasedPageable(page, pageSize, sortColumn);
        return movieRepository.findByTitleContains(pageable, search)
                .get().collect(Collectors.toList());
    }

    @Override
    public int countSearch(String search) {
        return movieRepository.countByTitleContains(search);
    }

    @Override
    public void update(int id, Movie movie) {
        movie.setId(id);
        movieRepository.save(movie);
    }
}
