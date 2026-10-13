package com.ecommerce.ecommerceapi.controller;

import com.ecommerce.ecommerceapi.constants.AppConstants;
import com.ecommerce.ecommerceapi.dto.PageResponse;
import com.ecommerce.ecommerceapi.dto.ProductRequest;
import com.ecommerce.ecommerceapi.dto.ProductResponse;

import com.ecommerce.ecommerceapi.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequest, categoryId));
    }

    @GetMapping("/public/products")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCT_BY) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder
    ) {
        return ResponseEntity.ok(productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<PageResponse<ProductResponse>> getProductsByCategory(@PathVariable Long categoryId,
                                                                                       @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) @Min(0) Integer pageNumber,
                                                                                       @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE)  @Min(1) @Max(20) Integer pageSize,
                                                                                       @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCT_BY) String sortBy,
                                                                                       @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder));
    }

}
