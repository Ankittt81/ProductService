package com.smartcart.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private String title;
    private String description;
    private Double basePrice;
    private String imageUrl;
}
