package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.comment.StoreRequest;
import com.hust.movie_review.models.Comment;

public interface ICommentService extends IService<Comment, Integer> {
    Comment insert(StoreRequest request);
}
