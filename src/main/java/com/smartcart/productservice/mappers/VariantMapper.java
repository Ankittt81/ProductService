package com.smartcart.productservice.mappers;

import com.smartcart.productservice.dtos.VariantResponseDto;
import com.smartcart.productservice.models.Product;
import com.smartcart.productservice.models.Status;
import com.smartcart.productservice.models.Variant;
import com.smartcart.productservice.utils.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VariantMapper {
    private JsonUtil  jsonUtil;
    public VariantMapper(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    public Variant toEntity(Product product, String sku, Map<String, String> attributes, Double price){
        Variant variant=new Variant();
        variant.setProduct(product);
        variant.setSku(sku);
        variant.setPrice(price);
        variant.setAttributes(jsonUtil.toJson(attributes));
        variant.setStatus(Status.ACTIVE);

        return variant;
    }
    public VariantResponseDto toDto(Variant  variant) {
        VariantResponseDto variantResponseDto = new VariantResponseDto();
        variantResponseDto.setVariantId(variant.getId());
        variantResponseDto.setProductTitle(variant.getProduct().getTitle());
        variantResponseDto.setSku(variant.getSku());
        variantResponseDto.setAttributes(jsonUtil.fromJson(variant.getAttributes()));
        variantResponseDto.setPrice(variant.getPrice());

        return variantResponseDto;
    }
}
