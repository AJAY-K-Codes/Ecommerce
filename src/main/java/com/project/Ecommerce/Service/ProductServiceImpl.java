package com.project.Ecommerce.Service;

import com.project.Ecommerce.Exceptions.APIException;
import com.project.Ecommerce.Exceptions.ResourceNotFoundException;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Model.Product;
import com.project.Ecommerce.Payload.ProductDTO;
import com.project.Ecommerce.Repository.CategoryRepository;
import com.project.Ecommerce.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addProducts(ProductDTO productDTO, Long categoryid){
        Optional<Product> product =
                productRepository.findByProductNameAndCategory_CategoryId(
                        productDTO.getProductName(),
                        categoryid
                );

        if (product.isPresent()) {
            throw new APIException("Product already exists in this category");
        }
        Category category= categoryRepository.findById(categoryid)
          .orElseThrow(()->new ResourceNotFoundException("Category","categoryid",categoryid));
        Product ogproduct= modelMapper.map(productDTO,Product.class);
        ogproduct.setCategory(category);
        Double splprice = ogproduct.getProductPrice()-(ogproduct.getProductDiscount()*0.01)*ogproduct.getProductPrice();
        ogproduct.setProductSpecialPrice(splprice);
        productRepository.save(ogproduct);
    }
    @Override
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> {
                    ProductDTO dto = modelMapper.map(product, ProductDTO.class);

                    dto.setCategoryName(
                            product.getCategory().getCategoryName()
                    );

                    return dto;
                })
                .toList();

        return productDTOs;
    }
}
