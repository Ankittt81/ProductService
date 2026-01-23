package com.smartcart.productservice.dtos;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStatusDto {
    private Status status;
}
