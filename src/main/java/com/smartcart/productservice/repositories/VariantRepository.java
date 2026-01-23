package com.smartcart.productservice.repositories;

import com.smartcart.productservice.models.Status;
import com.smartcart.productservice.models.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<Variant,Long> {
    Variant save(Variant variant);

    Optional<Variant> findById(Long Id);

    List<Variant> findAllByProduct_Id(Long productId);

    List<Variant> findByProductIdAndStatus(Long productId, Status status);

    Optional<Variant> findBySku(String sku);
}
