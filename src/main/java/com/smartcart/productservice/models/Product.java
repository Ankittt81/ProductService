package com.smartcart.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private Double basePrice;
    private String imageUrl;
    @ManyToOne
    private Category category;
    @Enumerated(EnumType.STRING)
    private Status status;
}



/*
  1                 1
Product         Category    => M:1
   M                 1
 */
