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
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Double productDiscount;
    private String imageUrl;
    private Double productSpecialPrice;
    private int productQuantity;


    @ManyToOne
    @JoinColumn(name="Category_Id")
    private Category category;


}
