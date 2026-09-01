package com.project.Ecommerce.Repository;

import com.project.Ecommerce.Model.Category;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByCategoryName(@NotBlank(message = "Category name needed") String categoryName);
}
