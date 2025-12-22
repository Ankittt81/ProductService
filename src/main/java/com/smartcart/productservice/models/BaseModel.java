package com.smartcart.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    private Long Id;
    private Date CreatedAt;
    private Date LastModifiedAt;
}
