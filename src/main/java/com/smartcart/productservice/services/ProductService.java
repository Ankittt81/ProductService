package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getSingleProduct(Long productId);
    public Product createProduct(Product product);
    public Product replaceProduct(Long productId,Product product);
    public void deleteProduct(Long productId);
}
