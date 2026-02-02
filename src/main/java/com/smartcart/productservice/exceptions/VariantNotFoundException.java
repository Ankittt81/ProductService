package com.smartcart.productservice.exceptions;

public class VariantNotFoundException extends RuntimeException{
    public VariantNotFoundException(String message) {
        super(message);
    }
}
