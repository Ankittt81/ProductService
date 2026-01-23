package com.smartcart.productservice.controllers;

import com.smartcart.productservice.commons.AuthCommon;
import com.smartcart.productservice.dtos.ProductRequestDto;
import com.smartcart.productservice.dtos.ProductResponseDto;
import com.smartcart.productservice.dtos.ProductStatusDto;
import com.smartcart.productservice.dtos.UpdateProductDto;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private ProductMapper  productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getSingleProduct(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        Product product=productService.getSingleProduct(productId);
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

    @PostMapping()
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product=productService.createProduct(productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getBasePrice(),
                productRequestDto.getCategoryId(),
                productRequestDto.getImageUrl()
                );
        return productMapper.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto replaceProduct(@PathVariable("id") Long productId,@RequestBody UpdateProductDto updateProductDto) throws ProductNotFoundException{
        Product product= productService.replaceProduct(productId,updateProductDto);
        return productMapper.toDto(product);
    }

    @PatchMapping("/{id}/status")
    public ProductResponseDto updateProductStatus(@PathVariable("id") Long productId, @RequestBody ProductStatusDto productStatusDto) throws ProductNotFoundException {
       Product product= productService.updateProductStatus(productId,productStatusDto);

       return productMapper.toDto(product);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long productId,@RequestBody UpdateProductDto updateProductDto) throws ProductNotFoundException{
        Product product=productService.updateProduct(productId,updateProductDto);
        return productMapper.toDto(product);
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
