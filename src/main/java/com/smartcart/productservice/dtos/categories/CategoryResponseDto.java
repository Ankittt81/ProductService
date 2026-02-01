package com.smartcart.productservice.dtos.categories;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long categoryId;
    private String title;
    private Long parentId;
    private String parentTitle;
    private Status status;

}
