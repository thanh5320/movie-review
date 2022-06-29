package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.category.StoreRequest;
import com.hust.movie_review.data.request.category.UpdateRequest;
import com.hust.movie_review.models.Category;

public interface ICategoryService extends IService<Category, Integer> {
    Category insert(StoreRequest request);

    Category update(UpdateRequest request);
}
