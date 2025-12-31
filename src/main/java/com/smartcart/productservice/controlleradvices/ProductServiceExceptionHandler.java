package com.smartcart.productservice.controlleradvices;

import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.exceptions.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>(
                productNotFoundException.getProductId() +" is an invalid Id,Please pass a valid id",
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFound(CategoryNotFoundException  categoryNotFoundException){
        String message;
        if(categoryNotFoundException.getCategoryId()!=null){
            message=categoryNotFoundException.getCategoryId() +" is an Invalid Id! Please enter valid Id";
        }else if(categoryNotFoundException.getCategoryName()!=null){
            message=categoryNotFoundException.getCategoryName() +" is an Invalid Title! Please enter valid Title";
        }else {
            message = "Category not found";
        }
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleConflict(ResourceAlreadyExistsException  resourceAlreadyExistsException){
        return new ResponseEntity<>(resourceAlreadyExistsException.getMessage(),HttpStatus.CONFLICT);
    }
}
