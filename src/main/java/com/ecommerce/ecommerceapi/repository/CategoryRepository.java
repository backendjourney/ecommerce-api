package com.ecommerce.ecommerceapi.repository;

import com.ecommerce.ecommerceapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByCategoryNameContainingIgnoreCase(String categoryName);
}
