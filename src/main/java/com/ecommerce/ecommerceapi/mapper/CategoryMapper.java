package com.ecommerce.ecommerceapi.mapper;

import com.ecommerce.ecommerceapi.dtos.CategoryRequest;
import com.ecommerce.ecommerceapi.dtos.CategoryResponse;
import com.ecommerce.ecommerceapi.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toReponseList(List<Category> categories);

    @Mapping(target = "categoryId", ignore = true)
    Category toEntity(CategoryRequest request);

    @Mapping(target = "categoryId", ignore = true)
    void updateEntity(CategoryRequest request, @MappingTarget Category category);
}
