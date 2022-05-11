package com.hust.movie_review.service;

import com.hust.movie_review.config.exception.ApiException;
import com.hust.movie_review.data.request.category.StoreRequest;
import com.hust.movie_review.data.request.category.UpdateRequest;
import com.hust.movie_review.models.Category;
import com.hust.movie_review.repositories.CategoryRepository;
import com.hust.movie_review.service.template.ICategoryService;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public Category update(UpdateRequest request) {
        int id = request.getId();
        Optional<Category> optional = categoryRepository.findById(id);

        if(optional.isEmpty()){
            throw new ApiException("Không tìm thấy nhãn có id tương ứng");
        }

        Category category = optional.get();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    @SneakyThrows
    @Override
    public boolean delete(int id) {
        Optional<Category> optional = categoryRepository.findById(id);

        if(optional.isEmpty()){
            throw new ApiException("Không tìm thấy nhãn có id tương ứng");
        }

        try {
            categoryRepository.delete(optional.get());
        } catch (Exception exception){
            return false;
        }

        return true;
    }
}
