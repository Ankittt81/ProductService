package com.smartcart.productservice.mappers;

import com.smartcart.productservice.dtos.categories.CategoryRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryResponseDto;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Status;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {

    public CategoryResponseDto toDto(Category category){
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
        categoryResponseDto.setCategoryId(category.getId());
        categoryResponseDto.setTitle(category.getTitle());
        categoryResponseDto.setParentId(category.getParent().getId());
        categoryResponseDto.setParentTitle(category.getParent().getTitle());
        categoryResponseDto.setStatus(category.getStatus());
        return categoryResponseDto;
    }

    public Category toEntity(CategoryRequestDto  categoryRequestDto,Category parent){
        Category category=new Category();
        category.setTitle(categoryRequestDto.getTitle());
        category.setParent(parent);
        category.setStatus(Status.ACTIVE);
        return category;
    }
}
