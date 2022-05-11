package com.hust.movie_review.controllers;

import com.hust.movie_review.common.data.response.DfResponse;
import com.hust.movie_review.data.request.category.StoreRequest;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.service.template.ICategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("listing")
    public DfResponse<List<Category>> listing(){
        return DfResponse.okEntity(categoryService.listing());
    }

    @PostMapping("detail/{id}")
    public DfResponse<Category> detail(@PathVariable int id){
        return DfResponse.okEntity(categoryService.detail(id));
    }

    @PostMapping("store")
    public DfResponse<Category> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(categoryService.insert(request));
    }
}
