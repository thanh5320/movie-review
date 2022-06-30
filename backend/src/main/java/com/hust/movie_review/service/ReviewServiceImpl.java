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

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl extends BaseService<Review, Integer> implements IReviewService {
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

        // calc movie rating
        List<Review> lst = reviewRepository.findByUserId(user.getId());
        lst.forEach(System.out::println);
        float ratingAvg = 0;
        for(Review itm: lst){
            ratingAvg += (float) itm.getRating();
        }

        ratingAvg = ratingAvg / lst.size();
        movie.setRating(ratingAvg);
        movieRepository.save(movie);

        return review;
    }

    @Override
    public Boolean checkReview(int movieId, int userId) {
        List<Review> lst = reviewRepository.findByUserId(userId);
        for(Review itm: lst){
            if(itm.getMovie().getId() == movieId){
                return false;
            }
        }

        return true;
    }
}
