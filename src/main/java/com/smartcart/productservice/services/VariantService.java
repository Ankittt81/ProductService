package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Variant;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface VariantService {
    Variant getVariantByProductId(Long productId);

    Variant getVariantBySku(String sku);

    Variant getVariantById(Long variantId);

    Variant createVariant(Long productId, String sku, Map<String,String> attributes,Double price);

    Variant updateVariant(Long variantId, Map<String,String> attributes, Double price);

    Variant deleteVariant(Long variantId);
}
