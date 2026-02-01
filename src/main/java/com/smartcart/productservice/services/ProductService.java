package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.products.ProductRequestDto;
import com.smartcart.productservice.dtos.products.ProductStatusDto;
import com.smartcart.productservice.dtos.products.UpdateProductDto;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long productId) throws ProductNotFoundException;
    public Product createProduct(ProductRequestDto dto);
    public List<Product> getAllProductsByCategory(Long categoryId);
    public Product updateProduct(Long productId, UpdateProductDto dto);
    public Product updateProductStatus(Long productId, ProductStatusDto dto) throws ProductNotFoundException;
    public Product deleteProduct(Long productId);
}
