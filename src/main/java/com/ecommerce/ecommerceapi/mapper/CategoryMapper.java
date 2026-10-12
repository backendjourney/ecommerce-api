package com.ecommerce.ecommerceapi.mapper;

import com.ecommerce.ecommerceapi.dto.CategoryRequest;
import com.ecommerce.ecommerceapi.dto.CategoryResponse;
import com.ecommerce.ecommerceapi.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);

    @Mapping(target = "categoryId", ignore = true)
    Category toEntity(CategoryRequest request);

    @Mapping(target = "categoryId", ignore = true)
    void updateEntity(CategoryRequest request, @MappingTarget Category category);
}
