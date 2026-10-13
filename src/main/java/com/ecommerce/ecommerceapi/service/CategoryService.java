package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.dto.CategoryRequest;
import com.ecommerce.ecommerceapi.dto.CategoryResponse;
import com.ecommerce.ecommerceapi.dto.PageResponse;


public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    void deleteCategory(Long categoryId);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    PageResponse<CategoryResponse> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
