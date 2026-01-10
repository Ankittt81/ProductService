package com.smartcart.productservice.services;

import com.smartcart.productservice.exceptions.ProductNotFoundException;
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
    private ProductRepository  productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public Product createProduct(Product product)  {
        if(product.getId()!=null){
            //Exception for wrong http call
            throw new IllegalArgumentException("ID must not be provided while creating a new product");
        }
        Category category=product.getCategory();
        if(category.getId()==null){
          Category savedCategory=  categoryRepository.save(category);
            product.setCategory(savedCategory);
        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw  new ProductNotFoundException(productId);
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(productId);
        }
        else{
            productRepository.deleteById(productId);
            System.out.println("Product deleted");
        }
    }
}
