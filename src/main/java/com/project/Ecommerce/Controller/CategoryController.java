package com.project.Ecommerce.Controller;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Service.CategoryInterface;
import com.project.Ecommerce.Service.Categoryserviceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
     private final CategoryInterface catoryInterface;

     public CategoryController(CategoryInterface catoryInterface) {
         this.catoryInterface = catoryInterface;
     }

     @GetMapping("/api/public/categories")
     public ResponseEntity<List<Category>> getallCategory()
     {
          List<Category> categories= catoryInterface.getallCategories();
          return new ResponseEntity<>(categories,HttpStatus.OK);
     }
     @PostMapping("/api/public/categories")
     public ResponseEntity<String> addCategories(@RequestBody Category category){
          catoryInterface.createCategory(category);
          return new ResponseEntity<>("Category Added Sucessfully",HttpStatus.CREATED);
     }
     @DeleteMapping("/api/admin/categories/{categoryId}")
     public ResponseEntity<String> deleteCatogories(@PathVariable Long categoryId){
          try {
               String status = catoryInterface.DeletecategoryId(categoryId);
               return new ResponseEntity<>(status, HttpStatus.OK);
          }catch(ResponseStatusException e){
               return new ResponseEntity<>(e.getReason(),e.getStatusCode());
          }
     }
     @PutMapping("/api/admin/categories/{id}")
     public ResponseEntity<String> UpdateCategories(@PathVariable Long id,
                                                    @RequestBody Category category)
     {
          try{
               Category status = catoryInterface.UpdateCategories(id,category);
               return new ResponseEntity<>(status.getCategoryName()+" "+"Updated Successfullly",HttpStatus.OK);
          }catch (ResponseStatusException e){
               return new ResponseEntity<>(e.getReason(),e.getStatusCode());
          }
     }

}
