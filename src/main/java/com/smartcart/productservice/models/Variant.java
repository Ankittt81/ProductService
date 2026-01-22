package com.smartcart.productservice.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Variant extends BaseModel {
    @ManyToOne
    private Product product;
    @Column(nullable = false,unique = true)
    private String sku;
    @Column(columnDefinition = "json")
    private String attributes;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;
}


/*
   1               1
Variant         Product
    m               1
 */
