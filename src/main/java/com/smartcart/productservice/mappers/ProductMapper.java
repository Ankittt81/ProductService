package com.smartcart.productservice.mappers;

import com.smartcart.productservice.dtos.products.ProductRequestDto;
import com.smartcart.productservice.dtos.products.ProductResponseDto;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Status;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDto dto, Category category){
        Product product=new Product();
        product.setCategory(category);
        product.setDescription(dto.getDescription());
        product.setTitle(dto.getTitle());
        product.setBasePrice(dto.getBasePrice());
        product.setImageUrl(dto.getImageUrl());
        product.setStatus(Status.ACTIVE);
        return product;
    }

    public ProductResponseDto toDto(Product product){
        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setProductId(product.getId());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setBasePrice(product.getBasePrice());
        productResponseDto.setCategoryName(product.getCategory().getTitle());
        productResponseDto.setStatus(product.getStatus());

        return productResponseDto;
    }
}
