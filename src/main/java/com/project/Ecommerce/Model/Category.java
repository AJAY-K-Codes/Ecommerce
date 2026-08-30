package com.project.Ecommerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_id")
    @SequenceGenerator(name="order_id",sequenceName = "order_sequence",allocationSize = 1)
    private Long categoryId;
    private String categoryName;
}
