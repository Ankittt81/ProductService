package com.smartcart.productservice.services;

import com.smartcart.productservice.mappers.VariantMapper;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Status;
import com.smartcart.productservice.models.Variant;
import com.smartcart.productservice.repositories.ProductRepository;
import com.smartcart.productservice.repositories.VariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService {
    private VariantMapper  variantMapper;
    private ProductRepository productRepository;
    private VariantRepository variantRepository;

    public VariantServiceImpl(ProductRepository productRepository,
                              VariantRepository variantRepository,
                              VariantMapper variantMapper) {
        this.productRepository = productRepository;
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
    }
    @Override
    public List<Variant> getVariantsByProductId(Long productId) {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            return null;
        }
        Product product=optionalProduct.get();
        List<Variant> variants=variantRepository.findByProductIdAndStatus(product.getId(),Status.ACTIVE);
        return variants;
    }

    @Override
    public Variant getVariantBySku(String sku) {
        Optional<Variant> optionalVariant=variantRepository.findBySku(sku);

        if(optionalVariant.isEmpty()){
            return null;
        }
        return optionalVariant.get();
    }

    @Override
    public Variant getVariantById(Long variantId) {
        Optional<Variant>  optionalVariant = variantRepository.findById(variantId);
        if(optionalVariant.isEmpty()){
            return null;
        }
        return optionalVariant.get();
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
    public Variant updateVariantPrice(Long variantId,Double price) {
        Optional<Variant> optionalVariant=variantRepository.findById(variantId);
        if(optionalVariant.isEmpty()){
            return null;
        }
        Variant variant=optionalVariant.get();
        variant.setPrice(price);

           return variantRepository.save(variant);
    }

    @Override
    public Variant updateVariantStatus(Long variantId,Status status) {
        Optional<Variant> optionalVariant=variantRepository.findById(variantId);
        if(optionalVariant.isEmpty()){
            return null;
        }
        Variant variant=optionalVariant.get();
        variant.setStatus(status);

        return variantRepository.save(variant);
    }
}
