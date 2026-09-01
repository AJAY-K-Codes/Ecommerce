package com.project.Ecommerce.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Category name needed")
    private String categoryName;
}
