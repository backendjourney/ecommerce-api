package com.ecommerce.ecommerceapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Category name must not be blank")
        @Size(min = 5, message = "Category name must contain at least 5 characters")
        String categoryName
) {

}
