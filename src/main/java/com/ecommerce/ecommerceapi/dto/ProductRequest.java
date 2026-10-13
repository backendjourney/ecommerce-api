package com.ecommerce.ecommerceapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name must not be blank")
        String productName,
        @NotNull(message = "Quantity is required")
        @PositiveOrZero(message = "Quantity must be zero or greater")
        Integer quantity,
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price,
        @DecimalMin(value = "0", message = "Discount must be at least 0")
        @DecimalMax(value = "100", message = "Discount must not exceed 100")
        BigDecimal discount,
        String description
) {
}
