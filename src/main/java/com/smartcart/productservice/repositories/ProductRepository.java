package com.smartcart.productservice.repositories;

import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long aLong);
    List<Product> findByCategoryInAndStatus(List<Category> categories,Status status);
    List<Product> findByCategoryAndStatus(Category category, Status status);

    Optional<Product> findByTitle(String title);
   boolean existsByTitleIgnoreCaseAndCategory(String title, Category category);
    List<Product> findAllByStatus(Status status);

    Product save(Product product);

    @Override
    void deleteById(Long aLong);


}
