package com.project.Ecommerce.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    @NotBlank(message = "Not Empty")
    private String categoryName;
    @Size(min=4)
    @NotBlank
    private String categoryDescription;
}
