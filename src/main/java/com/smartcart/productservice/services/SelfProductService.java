package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.products.ProductRequestDto;
import com.smartcart.productservice.dtos.products.ProductStatusDto;
import com.smartcart.productservice.dtos.products.UpdateProductDto;
import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.mappers.ProductMapper;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Status;
import com.smartcart.productservice.repositories.CategoryRepository;
import com.smartcart.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary
public class SelfProductService implements ProductService{
    private final ProductMapper productMapper;
    private ProductRepository  productRepository;
    private CategoryService categoryService;

    public SelfProductService(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(ProductRequestDto dto)  {
        Category category=categoryService.getCategoryById(dto.getCategoryId());

        if(category.getStatus()!= Status.ACTIVE){
            throw new RuntimeException("Category is not active");
        }
        //  category check whehter it is parent or leaf
        if(categoryService.hasChildren(category)){
            throw new RuntimeException("Parent category cannot assigned to product");
        }

        Product product=productMapper.toEntity(dto,category);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByStatus(Status.ACTIVE);
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct= productRepository.findById(productId);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(productId);
        }
        return optionalProduct.get();
    }

    public List<Product> getAllProductsByCategory(Long categoryId) {
        Category category=categoryService.getCategoryById(categoryId);
        if (category.getStatus() != Status.ACTIVE) {
            throw new RuntimeException("Category is not active");
        }
        if (!categoryService.hasChildren(category)) {
            return productRepository
                    .findByCategoryAndStatus(category, Status.ACTIVE);
        }
        List<Category> categories=categoryService.getLeafCategories(category);
        List<Product> products=productRepository.findByCategoryInAndStatus(categories, Status.ACTIVE);
        return products;
    }

    @Override
    public Product updateProduct(Long productId, UpdateProductDto dto){
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

    @Override
    public Product deleteProduct(Long productId) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(productId);
        }
        Product product=optionalProduct.get();
        product.setStatus(Status.DELETED);
        return productRepository.save(product);
    }
}
