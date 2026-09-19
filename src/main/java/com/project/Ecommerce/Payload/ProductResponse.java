package com.project.Ecommerce.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    List<ProductDTO> content;

    ProductDTO DtoContent;
}
