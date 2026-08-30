package com.project.Ecommerce.Service;

import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        return categoryRepository.findAll();

    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public String DeletecategoryId(Long categoryId) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category=categories
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found"));
        categoryRepository.delete(category);
        return categoryId+" "+"Removed Successfully";
    }

    @Override
    public Category UpdateCategories(Long categoryId,Category upcategory) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category = categories
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        category.setCategoryName(upcategory.getCategoryName());
        categoryRepository.save(category);
        return category;
    }
}