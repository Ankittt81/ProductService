package com.smartcart.productservice.services;

import com.smartcart.productservice.mappers.VariantMapper;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Variant;
import com.smartcart.productservice.repositories.ProductRepository;
import com.smartcart.productservice.repositories.VariantRepository;
import com.smartcart.productservice.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService {
    private final JsonUtil jsonUtil;
    private VariantMapper  variantMapper;
    private ProductRepository productRepository;
    private VariantRepository variantRepository;

    public VariantServiceImpl(ProductRepository productRepository,
                              JsonUtil jsonUtil,
                              VariantRepository variantRepository,
                              VariantMapper variantMapper) {
        this.productRepository = productRepository;
        this.jsonUtil = jsonUtil;
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
    }
    @Override
    public Variant getVariantByProductId(Long productId) {
        return null;
    }

    @Override
    public Variant getVariantBySku(String sku) {
        return null;
    }

    @Override
    public Variant getVariantById(Long variantId) {
        return null;
    }

    @Override
    public Variant createVariant(Long productId, String sku, Map<String, String> attributes, Double price) {
        Optional<Product> productOptional=productRepository.findById(productId);
        if(productOptional.isEmpty()){
            return null;
        }
        Product product=productOptional.get();
        Variant variant=variantMapper.toEntity(product,sku,attributes,price);
        return variantRepository.save(variant);
    }

    @Override
    public Variant updateVariant(Long variantId, Map<String, String> attributes, Double price) {
        return null;
    }

    @Override
    public Variant deleteVariant(Long variantId) {
        return null;
    }
}
