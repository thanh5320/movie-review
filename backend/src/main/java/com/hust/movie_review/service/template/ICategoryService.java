package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.category.StoreRequest;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.models.Movie;

public interface ICategoryService extends IService<Category> {
    Category insert(StoreRequest obj);

    void update(int id, Object movie);
}
