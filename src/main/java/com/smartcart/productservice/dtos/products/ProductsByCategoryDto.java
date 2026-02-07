package com.smartcart.productservice.dtos.products;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.smartcart.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "categoryId",
        "categoryName",
        "products"
})
public class ProductsByCategoryDto {
    private Long categoryId;
    private String categoryName;
    private List<Product> products;
}
