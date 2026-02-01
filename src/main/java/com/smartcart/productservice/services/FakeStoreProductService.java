package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.products.FakeStoreProductDto;
import com.smartcart.productservice.dtos.products.ProductStatusDto;
import com.smartcart.productservice.dtos.products.UpdateProductDto;
import com.smartcart.productservice.exceptions.ProductNotFoundException;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
//@Primary
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
    public Product createProduct(String title,String description,Double basePrice, Long categoryId,String imageUrl) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, UpdateProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, UpdateProductDto dto) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product updateProductStatus(Long productId, ProductStatusDto dto) throws ProductNotFoundException {
        return null;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {

        if(fakeStoreProductDto==null){
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setBasePrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category  category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
