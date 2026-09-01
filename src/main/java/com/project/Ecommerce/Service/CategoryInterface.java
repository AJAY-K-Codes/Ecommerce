package com.project.Ecommerce.Service;

import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Payload.CategoryDTO;
import com.project.Ecommerce.Payload.CategoryResponse;

import java.util.List;

public interface CategoryInterface {
    CategoryResponse getallCategories();
    CategoryDTO createCategory(CategoryDTO categoryDto);
    String DeletecategoryId(Long categoryId);
    Category UpdateCategories(Long categoryId,Category upcategory);
}
