package com.ecommerce.ecommerceapi.service.impl;

import com.ecommerce.ecommerceapi.model.Category;
import com.ecommerce.ecommerceapi.repository.CategoryRepository;
import com.ecommerce.ecommerceapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);

    }

    @Transactional
    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id: " + categoryId + " not found"));
        categoryRepository.delete(category);
        return "Category with id: " + categoryId + " has been deleted";
    }

    @Transactional
    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id: " + categoryId + " does not exist"));
        existingCategory.setCategoryName(category.getCategoryName());
        return existingCategory;
    }
}
