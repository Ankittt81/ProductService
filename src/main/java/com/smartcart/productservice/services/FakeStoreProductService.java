package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.FakeStoreProductDto;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity= restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDtos =responseEntity.getBody();

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
           Product product= convertFakeStoreDtoToProduct(fakeStoreProductDto);
           products.add(product);
        }

        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) {
       ResponseEntity<FakeStoreProductDto> responseEntity= restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId,
                FakeStoreProductDto.class);
        return convertFakeStoreDtoToProduct(responseEntity.getBody());
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {

        if(fakeStoreProductDto==null){
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category  category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
