package com.ecommerce.ecommerceapi.service.impl;

import com.ecommerce.ecommerceapi.dto.PageResponse;
import com.ecommerce.ecommerceapi.dto.ProductRequest;
import com.ecommerce.ecommerceapi.dto.ProductResponse;
import com.ecommerce.ecommerceapi.exception.ResourceNotFoundException;
import com.ecommerce.ecommerceapi.mapper.ProductMapper;
import com.ecommerce.ecommerceapi.model.Category;
import com.ecommerce.ecommerceapi.model.Product;
import com.ecommerce.ecommerceapi.repository.CategoryRepository;
import com.ecommerce.ecommerceapi.repository.ProductRepository;
import com.ecommerce.ecommerceapi.service.ProductService;
import com.ecommerce.ecommerceapi.util.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("productId", "productName");

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductResponse addProduct(ProductRequest productRequest, Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        Product product = productMapper.toEntity(productRequest);
        product.setCategory(category);
        product.recalculateSpecialPrice();
        product.setImage("default.png");

        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Pageable pageable = PaginationUtils.buildPageable(pageNumber, pageSize, sortBy, sortOrder, ALLOWED_SORT_FIELDS);

        Page<Product> productPage = productRepository.findAll(pageable);

        return PageResponse.from(productPage.map(productMapper::toResponse));
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ProductResponse> getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Pageable pageable = PaginationUtils.buildPageable(pageNumber, pageSize, sortBy, sortOrder, ALLOWED_SORT_FIELDS);

        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }

        Page<Product> productPage = productRepository.findByCategory_CategoryId(categoryId, pageable);
        return PageResponse.from(productPage.map(productMapper::toResponse));
    }

}
