package com.project.Ecommerce.Controller;

import com.project.Ecommerce.Payload.ProductDTO;
import com.project.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController{

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/{categoryid}/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long categoryid){
        productService.addProducts(productDTO,categoryid);
        return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
    }

    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDTO>> GetAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.FOUND);
    }

}
