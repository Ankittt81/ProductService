package com.smartcart.productservice.dtos.variants;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class VariantResponseDto {
    private Long variantId;
    private String productTitle;
    private String sku;
    private Map<String,String> attributes;
    private Double price;
    private Status status;
}
