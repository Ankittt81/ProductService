package com.smartcart.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"parent_id","title"})
        })

public class Category extends BaseModel{
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    @Enumerated(EnumType.STRING)
    private Status status;
}
