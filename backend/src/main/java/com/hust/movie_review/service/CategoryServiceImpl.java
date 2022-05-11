package com.hust.movie_review.service;

import com.hust.movie_review.data.request.category.StoreRequest;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.models.Movie;
import com.hust.movie_review.repositories.CategoryRepository;
import com.hust.movie_review.service.template.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listing() {
        return categoryRepository.findAll();
    }

    @Override
    public Category detail(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category insert(StoreRequest request) {
        Category category = new Category();
        category.setDescription(request.getDescription());
        category.setName(request.getName());

        return categoryRepository.save(category);
    }

    @Override
    public void update(int id, Object movie) {

    }

    @Override
    public void delete(int id) {

    }
}
