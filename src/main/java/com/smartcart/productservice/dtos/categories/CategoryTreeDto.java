package com.smartcart.productservice.dtos.categories;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryTreeDto {
    private Long id;
    private String title;
    private List<CategoryTreeDto> children=new ArrayList<>();
}
