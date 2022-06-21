package com.hust.movie_review.controllers;

import com.hust.movie_review.data.request.review.StoreRequest;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.models.Review;
import com.hust.movie_review.service.template.IReviewService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/review")
public class ReviewController {
    IReviewService reviewService;

    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("store")
    public DfResponse<Review> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(reviewService.insert(request));
    }

}
