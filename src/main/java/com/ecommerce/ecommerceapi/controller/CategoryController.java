package com.ecommerce.ecommerceapi.controller;

import com.ecommerce.ecommerceapi.dto.CategoryRequest;
import com.ecommerce.ecommerceapi.dto.CategoryResponse;
import com.ecommerce.ecommerceapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/public/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest));
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequest));
    }
}
