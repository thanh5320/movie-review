package com.hust.movie_review.service;

import com.hust.movie_review.config.exception.ApiException;
import com.hust.movie_review.data.request.comment.StoreRequest;
import com.hust.movie_review.models.Comment;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.models.User;
import com.hust.movie_review.repositories.ICommentRepository;
import com.hust.movie_review.repositories.IMovieRepository;
import com.hust.movie_review.repositories.IUserRepository;
import com.hust.movie_review.service.template.ICommentService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl extends BaseService<Comment, Integer> implements ICommentService {
    ICommentRepository commentRepository;
    IUserRepository userRepository;
    IMovieRepository movieRepository;

    public CommentServiceImpl(ICommentRepository commentRepository, IUserRepository userRepository, IMovieRepository movieRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }


    @Override
    @SneakyThrows
    public Comment insert(StoreRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if(optionalUser.isEmpty()){
            throw new ApiException("Can't find User with corresponding id");
        }

        User user = optionalUser.get();

        Optional<Movie> optionalMovie = movieRepository.findById(request.getMovieId());
        if(optionalMovie.isEmpty()){
            throw new ApiException("Can't find Movie with corresponding id");
        }

        Movie movie = optionalMovie.get();

        Comment comment = new Comment();
        comment.setComment(request.getComment())
                .setMovie(movie)
                .setUser(user);

        commentRepository.save(comment);
        return comment;
    }
}
