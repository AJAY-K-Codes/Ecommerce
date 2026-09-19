package com.project.Ecommerce.Repository;

import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductNameAndCategory_CategoryId(
            String productName,
            Long categoryId
    );

    List<Product> findByCategoryOrderByProductPriceAsc(Category category);

    List<Product> findByProductNameLikeIgnoreCase(String keyword);
}
