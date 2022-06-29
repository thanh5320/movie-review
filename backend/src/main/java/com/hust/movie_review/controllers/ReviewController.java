package com.hust.movie_review.controllers;

import com.hust.movie_review.data.mapper.ReviewMapper;
import com.hust.movie_review.data.request.review.StoreRequest;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.data.response.ReviewResponse;
import com.hust.movie_review.service.template.IReviewService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/review")
public class ReviewController {
    IReviewService reviewService;

    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("listing")
    public DfResponse<List<ReviewResponse>> listing(){
        return DfResponse.okEntity(
                ReviewMapper.toReviewResponses(reviewService.listing())
        );
    }

    @PostMapping("store")
    public DfResponse<ReviewResponse> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(
                ReviewMapper.toReviewResponse(reviewService.insert(request))
        );
    }

}
