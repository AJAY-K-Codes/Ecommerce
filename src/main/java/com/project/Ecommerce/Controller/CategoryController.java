package com.project.Ecommerce.Controller;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Payload.CategoryDTO;
import com.project.Ecommerce.Payload.CategoryResponse;
import com.project.Ecommerce.Service.CategoryInterface;
import com.project.Ecommerce.Service.Categoryserviceimpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

      private final CategoryInterface categoryInterface;

     public CategoryController(CategoryInterface catoryInterface) {
         this.categoryInterface = catoryInterface;
     }

     @GetMapping("/api/public/categories")
     public ResponseEntity<CategoryResponse> getallCategory(
             @RequestParam(name="pagenumber") int pagenumber,@RequestParam(name="pagesize") int pagesize
     )
     {
          CategoryResponse categories = categoryInterface.getallCategories(pagenumber,pagesize);
          return new ResponseEntity<>(categories,HttpStatus.OK);
     }
     @PostMapping("/api/public/categories")
     public ResponseEntity<String> addCategories(@Valid @RequestBody CategoryDTO categoryDto){
          categoryInterface.createCategory(categoryDto);
          return new ResponseEntity<>("Category Saved Successfully",HttpStatus.CREATED);
     }
     @DeleteMapping("/api/admin/categories/{categoryId}")
     public ResponseEntity<String> deleteCatogories(@PathVariable Long categoryId){
          String status = categoryInterface.DeletecategoryId(categoryId);
          return new ResponseEntity<>(status, HttpStatus.OK);
     }
     @PutMapping("/api/admin/categories/{id}")
     public ResponseEntity<String> UpdateCategories(@PathVariable Long id,
                                                    @RequestBody Category category)
     {     Category status = categoryInterface.UpdateCategories(id,category);
           return new ResponseEntity<>(status.getCategoryName()+" "+"Updated Successfullly",HttpStatus.OK);

     }

}
