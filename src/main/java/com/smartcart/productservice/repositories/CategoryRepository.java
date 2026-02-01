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

    // 1️⃣ Used in addCategory & updateCategory
//    SELECT EXISTS (
//            SELECT 1
//                    FROM category
//                    WHERE parent_id = ?
//                    AND title = ?
//    );
    boolean existsByParentAndTitle(Category parent, String title);

    // 2️⃣ Used to fetch root categories
//    SELECT *
//    FROM category
//    WHERE parent_id IS NULL
//    AND status = 'ACTIVE';
    List<Category> findByParentIsNullAndStatus(Status status);

    // 3️⃣ Used to fetch children categories
//    SELECT *
//    FROM category
//    WHERE parent_id = ?
//    AND status = ?;
    List<Category> findByParentAndStatus(Category parent, Status status);


//    SELECT *
//    FROM category
//    WHERE status = ?;
    List<Category> findAllByStatus(Status status);

    //“Is there at least ONE category whose parent is this category?”
//    SELECT 1
//    FROM categories
//    WHERE parent_id = 5
//    LIMIT 1;
    boolean existsByParent(Category parent);

    List<Category> findAllByParentAndStatus(Category parent, Status status);

    Category save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByTitle(String title);

    @Override
    void deleteById(Long aLong);
}
