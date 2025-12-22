package com.smartcart.productservice.controllers;

import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId){
        return productService.getSingleProduct(productId);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return null;
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId,@RequestBody Product product){
        return null;
    }
}
