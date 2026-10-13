package com.ecommerce.ecommerceapi.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long productId,
        String productName,
        String image,
        Integer quantity,
        BigDecimal price,
        BigDecimal discount,
        BigDecimal specialPrice,
        String description
) {
}
