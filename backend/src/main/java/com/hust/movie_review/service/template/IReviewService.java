package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.review.StoreRequest;
import com.hust.movie_review.models.Review;

public interface IReviewService extends IService<Review, Integer> {
    Review insert(StoreRequest request);
}
