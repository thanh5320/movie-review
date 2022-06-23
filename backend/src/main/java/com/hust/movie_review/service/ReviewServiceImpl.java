package com.hust.movie_review.service;

import com.hust.movie_review.config.exception.ApiException;
import com.hust.movie_review.data.request.review.StoreRequest;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.models.Review;
import com.hust.movie_review.models.User;
import com.hust.movie_review.repositories.IMovieRepository;
import com.hust.movie_review.repositories.IReviewRepository;
import com.hust.movie_review.repositories.IUserRepository;
import com.hust.movie_review.service.template.IReviewService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl extends BaseService<Review> implements IReviewService {
    IReviewRepository reviewRepository;
    IMovieRepository movieRepository;
    IUserRepository userRepository;

    public ReviewServiceImpl(IReviewRepository reviewRepository, IMovieRepository movieRepository, IUserRepository userRepository) {
        super(reviewRepository);
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public Review insert(StoreRequest request) {
        Review review = new Review();
        review.setRating(request.getRating());

        Optional<Movie> optionalMovie = movieRepository.findById(request.getMovieId());
        if(optionalMovie.isEmpty()){
            throw new ApiException("Can't find Movie with corresponding id");
        }
        Movie movie = optionalMovie.get();

        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if(optionalUser.isEmpty()){
            throw new ApiException("Can't find User with corresponding id");
        }
        User user = optionalUser.get();

        review.setMovie(movie);
        review.setUser(user);
        reviewRepository.save(review);

        return review;
    }
}
