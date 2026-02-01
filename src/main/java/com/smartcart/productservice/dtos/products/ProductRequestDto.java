package com.smartcart.productservice.dtos.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private String description;
    private Double basePrice;
    private String imageUrl;
    private Long categoryId;

}
