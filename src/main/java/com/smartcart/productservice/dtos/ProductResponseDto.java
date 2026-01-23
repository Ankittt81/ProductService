package com.smartcart.productservice.dtos;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long productId;
    private String title;
    private String description;
    private Double basePrice;
    private String categoryName;
    private String imageUrl;
    private Status status;
}
