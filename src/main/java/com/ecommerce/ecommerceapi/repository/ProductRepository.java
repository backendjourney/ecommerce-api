package com.ecommerce.ecommerceapi.repository;

import com.ecommerce.ecommerceapi.model.Category;
import com.ecommerce.ecommerceapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByCategory_CategoryId(Long categoryCategoryId, Pageable pageable);
}
