package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.repositories.ProductRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository  productRepository;
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
            return null;
        }
        Category category=product.getCategory();
        if(category.getId()==null){
          Category savedCategory=  productRepository.save(category);
            product.setCategory(savedCategory);
        }


        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }
}
