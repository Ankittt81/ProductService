package com.smartcart.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFoundException extends RuntimeException{
    private Long categoryId;
    private String categoryName;

    public CategoryNotFoundException(String categoryName){
        this.categoryName = categoryName;
    }

    public CategoryNotFoundException(long categoryId){
        this.categoryId = categoryId;
    }
}
