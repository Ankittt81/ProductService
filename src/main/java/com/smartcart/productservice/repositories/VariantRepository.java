package com.smartcart.productservice.repositories;

import com.smartcart.productservice.models.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant,Long> {
    Variant save(Variant variant);
}
