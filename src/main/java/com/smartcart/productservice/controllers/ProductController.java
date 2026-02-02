package com.smartcart.productservice.controllers;

import com.smartcart.productservice.commons.AuthCommon;
import com.smartcart.productservice.dtos.products.ProductRequestDto;
import com.smartcart.productservice.dtos.products.ProductResponseDto;
import com.smartcart.productservice.dtos.products.ProductStatusDto;
import com.smartcart.productservice.dtos.products.UpdateProductDto;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.mappers.ProductMapper;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper  productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping()
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product=productService.createProduct(productRequestDto);
        return productMapper.toDto(product);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getSingleProduct(@PathVariable("productId") Long productId)
            throws ProductNotFoundException {
        Product product=productService.getProductById(productId);
        return productMapper.toDto(product);
    }

    @GetMapping()
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();
        for(Product  product:products){
            productResponseDtos.add(productMapper.toDto(product));
        }
        return productResponseDtos;
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponseDto> getProductsByCategory(@PathVariable("categoryId") Long categoryId){
        List<Product> products=productService.getAllProductsByCategory(categoryId);
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();
        for(Product  product:products){
            productResponseDtos.add(productMapper.toDto(product));
        }
        return productResponseDtos;
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long productId,@RequestBody UpdateProductDto updateProductDto) throws ProductNotFoundException{
        Product product= productService.updateProduct(productId,updateProductDto);
        return productMapper.toDto(product);
    }

    @PatchMapping("/{id}/status")
    public ProductResponseDto updateProductStatus(@PathVariable("id") Long productId, @RequestBody ProductStatusDto productStatusDto) throws ProductNotFoundException {
       Product product= productService.updateProductStatus(productId,productStatusDto);

       return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDto deleteProduct(@PathVariable("id") Long productId){
        Product product=productService.deleteProduct(productId);
        return productMapper.toDto(product);
    }

    @GetMapping("/{productId}/{tokenValue}")
    public ResponseEntity<Product> getSingleProductAfterValidation(@PathVariable("productId") Long productId, @PathVariable("tokenValue") String tokenValue) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity=null;
        Product product=null;
        if(AuthCommon.validate(tokenValue)){
            product=productService.getProductById(productId);
            responseEntity=new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            responseEntity=new ResponseEntity<>(product,HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }
}
