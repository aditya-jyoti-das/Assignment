package com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.mapper;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.productdb.entity.Product;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}
