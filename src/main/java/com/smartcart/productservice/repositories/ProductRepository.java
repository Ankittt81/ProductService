package com.smartcart.productservice.repositories;

import com.smartcart.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long aLong);

    Optional<Product> findByTitle(String title);
    @Override
    List<Product> findAll();

    Product save(Product product);

    @Override
    void deleteById(Long aLong);


}
