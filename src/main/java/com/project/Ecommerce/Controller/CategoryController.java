package com.project.Ecommerce.Controller;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Payload.CategoryDTO;
import com.project.Ecommerce.Payload.CategoryResponse;
import com.project.Ecommerce.Service.CategoryInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

     private final CategoryInterface categoryInterface;

     public CategoryController(CategoryInterface categoryInterface) {
         this.categoryInterface = categoryInterface;
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
                                                    @RequestBody CategoryDTO category)
     {     CategoryDTO status = categoryInterface.UpdateCategories(id,category);
           return new ResponseEntity<>(status.getCategoryName()+" "+"Updated Successfullly",HttpStatus.OK);

     }

}
