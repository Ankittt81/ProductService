package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.ProductRequestDto;
import com.smartcart.productservice.dtos.ProductStatusDto;
import com.smartcart.productservice.dtos.UpdateProductDto;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.mappers.ProductMapper;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.repositories.CategoryRepository;
import com.smartcart.productservice.repositories.ProductRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary
public class SelfProductService implements ProductService{
    private final ProductMapper productMapper;
    private ProductRepository  productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(productId);

        if(optionalProduct.isEmpty()){
            //Exception handler
            throw new ProductNotFoundException(productId);
        }
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(String title,String description,Double basePrice, Long categoryId,String imageUrl)  {
        Optional<Category> optionalCategory=categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty()){
            return null;
        }
        Product product=productMapper.toEntity(title,description,basePrice,optionalCategory.get(),imageUrl);
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, UpdateProductDto dto) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw  new ProductNotFoundException(productId);
        }
        Product product=optionalProduct.get();
        product.setDescription(dto.getDescription());
        product.setBasePrice(dto.getBasePrice());
        product.setImageUrl(dto.getImageUrl());
        product.setTitle(dto.getTitle());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, UpdateProductDto dto) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw  new ProductNotFoundException(productId);
        }
        Product product=optionalProduct.get();
        if(dto.getTitle()!=null) product.setTitle(dto.getTitle());
        if(dto.getDescription()!=null) product.setDescription(dto.getDescription());
        if(dto.getBasePrice()!=null) product.setBasePrice(dto.getBasePrice());
        if(dto.getImageUrl()!=null) product.setImageUrl(dto.getImageUrl());
        return productRepository.save(product);
    }

    @Override
    public Product updateProductStatus(Long productId, ProductStatusDto dto) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(productId);
        }
        Product product=optionalProduct.get();
        product.setStatus(dto.getStatus());

        return product;
    }
}
