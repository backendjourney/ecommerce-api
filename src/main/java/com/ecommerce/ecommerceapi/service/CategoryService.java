package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.dto.CategoryRequest;
import com.ecommerce.ecommerceapi.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);
}
