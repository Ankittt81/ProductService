package com.smartcart.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UVariantRequestDto {
    private String productName;
    private Map<String,String> attributes;
    private Double price;
}
