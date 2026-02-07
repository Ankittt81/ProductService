package com.smartcart.productservice.exceptions;

public class UnAuthenticatedException extends RuntimeException{
    public UnAuthenticatedException(String message){
        super(message);
    }
}
