package com.smartcart.productservice.controlleradvices;

import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.exceptions.ResourceAlreadyExistsException;
import com.smartcart.productservice.exceptions.UnAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Internal Server Error",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException productNotFoundException){
        return new ResponseEntity<>(
                productNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFound(CategoryNotFoundException  categoryNotFoundException){
        String message=categoryNotFoundException.getMessage();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleConflict(ResourceAlreadyExistsException  resourceAlreadyExistsException){
        return new ResponseEntity<>(resourceAlreadyExistsException.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnAuthenticatedException.class)
    public ResponseEntity<String> handleUnaAuthentication(UnAuthenticatedException unAuthenticatedException){
        System.out.println(unAuthenticatedException.getMessage());
        return new ResponseEntity<>(unAuthenticatedException.getMessage(),HttpStatus.UNAUTHORIZED);
    }
}
