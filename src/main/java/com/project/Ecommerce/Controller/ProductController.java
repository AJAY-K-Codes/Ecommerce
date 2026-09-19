package com.project.Ecommerce.Controller;

import com.project.Ecommerce.Payload.ProductDTO;
import com.project.Ecommerce.Payload.ProductResponse;
import com.project.Ecommerce.Service.ProductService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
    public ResponseEntity<ProductResponse> GetAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.FOUND);
    }

    @GetMapping("/public/{Category_id}/products")
    public ResponseEntity<ProductResponse> FetchProductsByCategory(@PathVariable Long Category_id){
        return new ResponseEntity<>(productService.FetchProductById(Category_id),HttpStatus.FOUND);
    }

    @GetMapping("public/products/{keyword}")
    public ResponseEntity<ProductResponse> FetchProductByKeyword(@PathVariable String keyword){
        ProductResponse productResponse = productService.FetchProductBykeyword(keyword);
        return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }

    @PutMapping("admin/products/{productid}")
    public ResponseEntity<ProductDTO> UpdateProductById(
            @RequestBody ProductDTO productDTO,
            @PathVariable Long productid) {

        ProductDTO product = productService.updateProductById(productDTO, productid);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @DeleteMapping("admin/products/{productid}")
    public ResponseEntity<String> DeleteProductById(@PathVariable Long productid){
        productService.deleteProductbyId(productid);
        return new ResponseEntity<>(productid+" "+"Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("admin/products/{productid}/image")
    public ResponseEntity<ProductDTO> UpdateProductImage(@PathVariable Long productid,
                                                         @RequestParam("image")MultipartFile image) throws IOException {
        ProductDTO productDTO = productService.UpdateProductimage(productid,image);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }



}
