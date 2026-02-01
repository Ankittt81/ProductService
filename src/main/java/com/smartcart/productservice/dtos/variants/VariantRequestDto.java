package com.smartcart.productservice.dtos.variants;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class VariantRequestDto {
    private Long productId;
    private String sku;
    private Map<String,String> attributes;
    private Double price;
}
