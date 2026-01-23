package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.ProductRequestDto;
import com.smartcart.productservice.dtos.ProductStatusDto;
import com.smartcart.productservice.dtos.UpdateProductDto;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getSingleProduct(Long productId) throws ProductNotFoundException;
    public Product createProduct(String title,String description,Double basePrice, Long categoryId,String imageUrl);
    public Product replaceProduct(Long productId, UpdateProductDto dto) throws ProductNotFoundException;
    public Product updateProduct(Long productId, UpdateProductDto dto) throws ProductNotFoundException;
    public Product updateProductStatus(Long productId, ProductStatusDto dto) throws ProductNotFoundException;
}
