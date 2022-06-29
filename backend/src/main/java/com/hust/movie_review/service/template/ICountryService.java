package com.hust.movie_review.service.template;

import com.hust.movie_review.data.request.country.StoreRequest;
import com.hust.movie_review.data.request.country.UpdateRequest;
import com.hust.movie_review.models.Country;

public interface ICountryService extends IService<Country, String> {
    Country insert(StoreRequest request);

    Country update(UpdateRequest request);
}
