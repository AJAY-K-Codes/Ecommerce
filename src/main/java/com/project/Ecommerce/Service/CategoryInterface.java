package com.project.Ecommerce.Service;

import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Payload.CategoryDTO;
import com.project.Ecommerce.Payload.CategoryResponse;

public interface CategoryInterface {
    CategoryResponse getallCategories(int Pagenumber,int Pagesize);
    void createCategory(CategoryDTO categoryDto);
    String DeletecategoryId(Long categoryId);
    Category UpdateCategories(Long categoryId,Category upcategory);
}
