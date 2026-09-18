package com.project.Ecommerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ProductName;
    private String ProductDescription;
    private Double ProductPrice;
    private Double ProductDiscount;
    private String ImageUrl;
    private Double ProductSpecialPrice;
    private int ProductQuantity;
    private String CategoryName;

    @ManyToOne
    @JoinColumn(name="Category_Id")
    private Category category;


}
