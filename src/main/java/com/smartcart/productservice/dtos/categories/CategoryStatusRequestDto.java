package com.smartcart.productservice.dtos.categories;

import com.smartcart.productservice.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryStatusRequestDto {
    private Status  status;
}
