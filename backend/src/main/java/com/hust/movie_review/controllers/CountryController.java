package com.hust.movie_review.controllers;

import com.hust.movie_review.data.response.DfResponse;

import com.hust.movie_review.models.Country;
import com.hust.movie_review.service.template.ICountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {
    ICountryService countryService;

    public CountryController(ICountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("listing")
    public DfResponse<List<Country>> listing(){
        return DfResponse.okEntity(countryService.listing());
    }
}
