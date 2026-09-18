package com.project.Ecommerce.Service;

import com.project.Ecommerce.Exceptions.APIException;
import com.project.Ecommerce.Exceptions.ResourceNotFoundException;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Payload.CategoryDTO;
import com.project.Ecommerce.Payload.CategoryResponse;
import com.project.Ecommerce.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.Ecommerce.Payload.CategoryResponse;

import java.util.List;
import java.util.Optional;

@Service
public class Categoryserviceimpl implements CategoryInterface {

    private final CategoryRepository categoryRepository;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private CategoryResponse categoryResponse;

    public Categoryserviceimpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse getallCategories(int pagenumber,int pagesize) {
        Pageable pageable= PageRequest.of(pagenumber,pagesize);
        Page<Category> page=categoryRepository.findAll(pageable);

         if(page.isEmpty()){
             throw new APIException("Category is null");
         }
         List<CategoryDTO> categoryDto =page.stream()
                 .map(category ->modelMapper.map(category,CategoryDTO.class))
                 .toList();
         categoryResponse.setContent(categoryDto);
         return categoryResponse;

    }

    @Override
    public void createCategory(CategoryDTO categoryDTO){
        Category savedcategory = categoryRepository.findByCategoryName((categoryDTO.getCategoryName()));
        if (savedcategory!=null) {
            throw new APIException("Category name Already Exist!!!");
        }
        Category category=modelMapper.map(categoryDTO,Category.class);
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
    public CategoryDTO UpdateCategories(Long categoryId,CategoryDTO upcategory) {
        Optional<Category> categories = categoryRepository.findById(categoryId);
        Category category = categories
                     .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

        Category Updatecategory =modelMapper.map(upcategory,Category.class);
        Updatecategory.setCategoryName((category.getCategoryName()));
        categoryRepository.save(Updatecategory);
        return upcategory;
    }
}