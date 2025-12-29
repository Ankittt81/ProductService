package com.smartcart.productservice.repositories;

import com.smartcart.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Override
    List<Category> findAll();

    Category save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByTitle(String title);

    @Override
    void deleteById(Long aLong);
}
