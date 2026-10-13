package com.ecommerce.ecommerceapi.mapper;

import com.ecommerce.ecommerceapi.dto.ProductRequest;
import com.ecommerce.ecommerceapi.dto.ProductResponse;
import com.ecommerce.ecommerceapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "specialPrice", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequest productRequest);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "specialPrice", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntity(ProductRequest productRequest, @MappingTarget Product product);
}
