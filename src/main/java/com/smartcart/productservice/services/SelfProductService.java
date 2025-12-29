package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.repositories.CategoryRepository;
import com.smartcart.productservice.repositories.ProductRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
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
    public Product getSingleProduct(Long productId) {
        Optional<Product> optionalProduct= productRepository.findById(productId);

        if(optionalProduct.isEmpty()){
            //Exception handler
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        if(product.getId()!=null){
            //Exception for wrong http call
            return null;
        }
        Category category=product.getCategory();
        if(category.getId()==null){
          Category savedCategory=  categoryRepository.save(category);
            product.setCategory(savedCategory);
        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            //Exception
            return null;
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            System.out.println("Product not found");
            return;
        }
        else{
            productRepository.deleteById(productId);
            System.out.println("Product deleted");
        }
    }
}
