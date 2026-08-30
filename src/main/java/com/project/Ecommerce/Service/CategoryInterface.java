package com.project.Ecommerce.Service;

import com.project.Ecommerce.Model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryInterface {
    List<Category> getallCategories();
    void createCategory(Category category);


    String DeletecategoryId(Long categoryId);
    Category UpdateCategories(Long categoryId,Category upcategory);
}
