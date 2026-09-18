package com.project.Ecommerce.Service;

import com.project.Ecommerce.Payload.ProductDTO;

import java.util.List;

public interface ProductService {
      void addProducts(ProductDTO productDTO, Long categoryid);

      List<ProductDTO> getAllProducts();
}
