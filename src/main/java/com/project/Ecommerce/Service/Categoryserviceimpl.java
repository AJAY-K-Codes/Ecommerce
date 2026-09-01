package com.project.Ecommerce.Service;

import com.project.Ecommerce.Exceptions.APIException;
import com.project.Ecommerce.Exceptions.ResourceNotFoundException;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Categoryserviceimpl implements CategoryInterface {

    private final CategoryRepository categoryRepository;
    private final List<Category> categories;

    public Categoryserviceimpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categories = categoryRepository.findAll();
    }

    @Override
    public List<Category> getallCategories() {
         List<Category> category=categoryRepository.findAll();
         if(category.isEmpty()){
             throw new APIException("Category is null");
         }
         return category;

    }

    @Override
    public void createCategory(Category category){
        Category savedcategory = categoryRepository.findByCategoryName((category.getCategoryName()));
        if (savedcategory!=null) {
            throw new APIException("Category name Already Exist!!!");
        }

        categoryRepository.save(category);
    }

    @Override
    public String DeletecategoryId(Long categoryId) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category=categories
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        categoryRepository.delete(category);
        return categoryId+" "+"Removed Successfully";
    }

    @Override
    public Category UpdateCategories(Long categoryId,Category upcategory) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category = categories
                     .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

                             category.setCategoryName(upcategory.getCategoryName());
        categoryRepository.save(category);
        return category;
    }
}