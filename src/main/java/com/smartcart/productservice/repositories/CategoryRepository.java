package com.smartcart.productservice.repositories;

import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Status;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

   boolean existsByParentAndTitle(Category parent, String title);

   List<Category> findByParentIsNullAndStatus(Status status);

   List<Category> findByParentAndStatus(Category parent, Status status);


    List<Category> findAllByStatus(Status status);

    Category save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByTitle(String title);

    @Override
    void deleteById(Long aLong);
}
