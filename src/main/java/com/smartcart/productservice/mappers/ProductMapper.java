package com.smartcart.productservice.mappers;

import com.smartcart.productservice.dtos.ProductResponseDto;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Status;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(String title, String description, Double basePrice, Category category, String imageUrl){
        Product product=new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setBasePrice(basePrice);
        product.setCategory(category);
        product.setImageUrl(imageUrl);
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
