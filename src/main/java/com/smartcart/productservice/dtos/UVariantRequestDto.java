package com.smartcart.productservice.dtos;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UVariantRequestDto {
    private Double price;
    private Status status;
}
