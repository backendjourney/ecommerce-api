package com.ecommerce.ecommerceapi.service.impl;

import com.ecommerce.ecommerceapi.model.Category;
import com.ecommerce.ecommerceapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categoryList = new ArrayList<>();
    private Long categoryId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(categoryId++);
        categoryList.add(category);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryList.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryList.remove(category);
        return "Category with category id: " + category.getCategoryId() + " has been deleted";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existCategory = categoryList
                .stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        existCategory.setCategoryName(category.getCategoryName());
        return existCategory;
    }
}
