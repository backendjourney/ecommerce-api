package com.ecommerce.ecommerceapi.service.impl;

import com.ecommerce.ecommerceapi.dto.CategoryRequest;
import com.ecommerce.ecommerceapi.dto.CategoryResponse;
import com.ecommerce.ecommerceapi.dto.PageResponse;
import com.ecommerce.ecommerceapi.exception.APIException;
import com.ecommerce.ecommerceapi.exception.ResourceNotFoundException;
import com.ecommerce.ecommerceapi.mapper.CategoryMapper;
import com.ecommerce.ecommerceapi.model.Category;
import com.ecommerce.ecommerceapi.repository.CategoryRepository;
import com.ecommerce.ecommerceapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("categoryId", "categoryName");


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<CategoryResponse> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new APIException("Invalid sort field: " + sortBy);
        }

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return PageResponse.from(categoryPage.map(categoryMapper::toResponse));
    }

    @Transactional
    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        boolean isCategoryNameExisting = categoryRepository.existsByCategoryNameIgnoreCase(categoryRequest.categoryName());
        if (isCategoryNameExisting) {
            throw new APIException("Category name  already exists");
        }
        Category category = categoryMapper.toEntity(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);
        categoryRepository.delete(category);
    }

    @Transactional
    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {

        Category existingCategory = findCategoryById(categoryId);

        if (categoryRepository.existsByCategoryNameIgnoreCaseAndCategoryIdNot(categoryRequest.categoryName(), categoryId)) {
            throw new APIException("Category name already exists");
        }

        categoryMapper.updateEntity(categoryRequest, existingCategory);
        return categoryMapper.toResponse(existingCategory);
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    }
}
