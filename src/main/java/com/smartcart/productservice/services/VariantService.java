package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Status;
import com.smartcart.productservice.models.Variant;

import java.util.List;
import java.util.Map;


public interface VariantService {
    List<Variant> getVariantsByProductId(Long productId);

    Variant getVariantBySku(String sku);

    Variant getVariantById(Long variantId);

    Variant createVariant(Long productId, String sku, Map<String,String> attributes,Double price);

    Variant updateVariantPrice(Long variantId,Double price);

    Variant updateVariantStatus(Long variantId, Status status);
}
