package com.smartcart.productservice.controllers;

import com.smartcart.productservice.commons.AuthCommon;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId,@RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(productId,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}/{tokenValue}")
    public ResponseEntity<Product> getSingleProductAfterValidation(@PathVariable("productId") Long productId, @PathVariable("tokenValue") String tokenValue) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity=null;
        Product product=null;
        if(AuthCommon.validate(tokenValue)){
            product=productService.getSingleProduct(productId);
            responseEntity=new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            responseEntity=new ResponseEntity<>(product,HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }
}
