package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.dto.PageResponse;
import com.ecommerce.ecommerceapi.dto.ProductRequest;
import com.ecommerce.ecommerceapi.dto.ProductResponse;
import jakarta.validation.Valid;

public interface ProductService {
    ProductResponse addProduct(@Valid ProductRequest productRequest, Long categoryId);

    PageResponse<ProductResponse> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PageResponse<ProductResponse> getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
