package com.hust.movie_review.service;

import com.hust.movie_review.data.request.country.StoreRequest;
import com.hust.movie_review.data.request.country.UpdateRequest;
import com.hust.movie_review.models.Country;
import com.hust.movie_review.repositories.ICountryRepository;
import com.hust.movie_review.service.template.ICountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends BaseService<Country> implements ICountryService {
    ICountryRepository countryRepository;

    public CountryServiceImpl(ICountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    @Override
    public Country insert(StoreRequest request) {
        return null;
    }

    @Override
    public Country update(UpdateRequest request) {
        return null;
    }
}
