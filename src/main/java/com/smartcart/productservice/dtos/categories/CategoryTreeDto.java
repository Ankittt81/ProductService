package com.smartcart.productservice.dtos.categories;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "title",
        "children"
})
public class CategoryTreeDto {
    private Long id;
    private String title;
    private List<CategoryTreeDto> children=new ArrayList<>();
}
