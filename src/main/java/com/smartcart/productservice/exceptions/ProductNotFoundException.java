package com.smartcart.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException{
    private Long productId;
    private String message;
    public ProductNotFoundException(Long productId) {
        this.productId = productId;
    }
    public ProductNotFoundException(String message) {
        this.message = message;
    }

}
