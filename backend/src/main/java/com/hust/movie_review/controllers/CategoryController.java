package com.hust.movie_review.controllers;

import com.hust.movie_review.data.mapper.CategoryMapper;
import com.hust.movie_review.data.request.category.StoreRequest;
import com.hust.movie_review.data.request.category.UpdateRequest;
import com.hust.movie_review.data.response.CategoryResponse;
import com.hust.movie_review.data.response.DfResponse;
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

    @GetMapping("listing")
    public DfResponse<List<CategoryResponse>> listing(){
        return DfResponse.okEntity(CategoryMapper.toCategoryResponses(categoryService.listing()));
    }

    @GetMapping("detail/{id}")
    public DfResponse<CategoryResponse> detail(@PathVariable int id){
        return DfResponse.okEntity(CategoryMapper.toCategoryResponse(categoryService.detail(id)));
    }

    @PostMapping("store")
    public DfResponse<CategoryResponse> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(CategoryMapper.toCategoryResponse(categoryService.insert(request)));
    }

    @PostMapping("update")
    public DfResponse<CategoryResponse> update(@RequestBody @Valid UpdateRequest request){
        return DfResponse.okEntity(CategoryMapper.toCategoryResponse(categoryService.update(request)));
    }

    @PostMapping("delete/{id}")
    public DfResponse<String> delete(@PathVariable int id){
        boolean result = categoryService.delete(id);
        if(!result){
            return DfResponse.badRequest("Có lỗi xảy ra!");
        }

        return DfResponse.okEntity("Xóa thành công");
    }
}
