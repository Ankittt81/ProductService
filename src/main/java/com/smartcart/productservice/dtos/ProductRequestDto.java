package com.smartcart.productservice.dtos;

import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Status;
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
