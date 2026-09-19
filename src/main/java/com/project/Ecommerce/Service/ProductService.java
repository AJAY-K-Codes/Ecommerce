package com.project.Ecommerce.Service;

import com.project.Ecommerce.Payload.ProductDTO;
import com.project.Ecommerce.Payload.ProductResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
      void addProducts(ProductDTO productDTO, Long categoryid);

      ProductResponse getAllProducts();

      ProductResponse FetchProductById(Long categoryId);

      ProductResponse FetchProductBykeyword(String keyword);

      ProductDTO updateProductById(ProductDTO productDTO, Long productid);

      void deleteProductbyId(Long productid);

      ProductDTO UpdateProductimage(Long productid, MultipartFile image) throws IOException;
}
