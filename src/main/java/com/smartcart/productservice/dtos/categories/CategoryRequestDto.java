package com.smartcart.productservice.dtos.categories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    private String title;
    private Long parentId;
}
