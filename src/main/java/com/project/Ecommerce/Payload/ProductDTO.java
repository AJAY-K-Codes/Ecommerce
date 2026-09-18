package com.project.Ecommerce.Payload;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Not Blank")
    private String ProductName;
    private String ProductDescription;
    private Double ProductPrice;
    private Double ProductDiscount;
    private String ImageUrl;
    private Double ProductSpecialPrice;
    private String CategoryName;


}
