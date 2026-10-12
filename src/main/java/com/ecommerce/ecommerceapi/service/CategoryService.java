package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.dtos.CategoryRequest;
import com.ecommerce.ecommerceapi.dtos.CategoryResponse;
import com.ecommerce.ecommerceapi.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);
}
