package com.project.Ecommerce.Service;

import com.project.Ecommerce.Exceptions.APIException;
import com.project.Ecommerce.Exceptions.ResourceNotFoundException;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Payload.CategoryDTO;
import com.project.Ecommerce.Payload.CategoryResponse;
import com.project.Ecommerce.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Categoryserviceimpl implements CategoryInterface {

    private final CategoryRepository categoryRepository;
    @Autowired
    private  ModelMapper modelMapper;
    private final List<Category> categories;

    public Categoryserviceimpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categories = categoryRepository.findAll();
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse getallCategories() {
         List<Category> categories=categoryRepository.findAll();
         if(categories.isEmpty()){
             throw new APIException("Category is null");
         }
         List<CategoryDTO> categorydto= categories.stream()
                 .map(category ->modelMapper.map(category,CategoryDTO.class))
                 .toList();
         CategoryResponse categoryResponse = new CategoryResponse();
         categoryResponse.setContent(categorydto);
         return categoryResponse;

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category savedcategory = categoryRepository.findByCategoryName((categoryDTO.getCategoryName()));
        if (savedcategory!=null) {
            throw new APIException("Category name Already Exist!!!");
        }
        Category category=modelMapper.map(categoryDTO,Category.class);
        categoryRepository.save(category);
        return categoryDTO;
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