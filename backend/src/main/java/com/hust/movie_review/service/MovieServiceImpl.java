package com.hust.movie_review.service;

import com.hust.movie_review.common.OffsetBasedPageable;
import com.hust.movie_review.config.exception.ApiException;
import com.hust.movie_review.data.request.movie.StoreRequest;
import com.hust.movie_review.data.request.movie.UpdateRequest;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.repositories.ICategoryRepository;
import com.hust.movie_review.repositories.ICountryRepository;
import com.hust.movie_review.repositories.IMovieRepository;
import com.hust.movie_review.service.template.IMovieService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl extends BaseService<Movie, Integer> implements IMovieService {
    IMovieRepository movieRepository;
    ICountryRepository countryRepository;
    ICategoryRepository categoryRepository;

    public MovieServiceImpl(IMovieRepository movieRepository, ICountryRepository countryRepository, ICategoryRepository categoryRepository){
        super(movieRepository);
        this.movieRepository = movieRepository;
        this.countryRepository = countryRepository;
        this.categoryRepository = categoryRepository;
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

    @SneakyThrows
    @Override
    public Movie insert(StoreRequest request) {
        Movie movie = new Movie();

        Country country = countryRepository.findByCode(request.getCountry());
        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategory());
        if(optionalCategory.isEmpty()){
            throw new ApiException("Không tồn tại category có nhãn tương ứng");
        }

        Category category = optionalCategory.get();

        movie.setDirector(request.getDirector())
                .setTitle(request.getTitle())
                .setCountry(country)
                .setCategory(category)
                .setTrailer(request.getTrailer())
                .setReleaseYear(request.getReleaseYear())
                .setDescription(request.getDescription())
                .setThumbnail(request.getThumbnail())
                .setType(request.getType())
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

    @SneakyThrows
    @Override
    public Movie update(UpdateRequest request) {
        int id = request.getId();
        Optional<Movie> optional = movieRepository.findById(id);
        if(optional.isEmpty()){
            throw new ApiException("Không tìm thấy phim có id tương ứng");
        }

        Optional<Category> optionalCategory = categoryRepository.findById(request.getCategoryId());
        if(optionalCategory.isEmpty()){
            throw new ApiException("Không tìm thấy category có id tương ứng");
        }

        Category category = optionalCategory.get();

        Optional<Country> optionalCountry = Optional.ofNullable(countryRepository.findByCode(request.getCountryCode()));
        Country country = optionalCountry.get();

        Movie movie = optional.get();
        movie.setYear(request.getYear());
        movie.setDescription(request.getDescription());
        movie.setTrailer(request.getTrailer());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setCountry(country);
        movie.setCategory(category);

        return movie;
    }
}
