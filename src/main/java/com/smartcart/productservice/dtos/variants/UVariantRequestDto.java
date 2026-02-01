package com.smartcart.productservice.dtos.variants;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UVariantRequestDto {
    private Double price;
    private Status status;
}
