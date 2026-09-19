package com.project.Ecommerce.Service;

import com.project.Ecommerce.Exceptions.APIException;
import com.project.Ecommerce.Exceptions.ResourceNotFoundException;
import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Model.Product;
import com.project.Ecommerce.Payload.ProductDTO;
import com.project.Ecommerce.Payload.ProductResponse;
import com.project.Ecommerce.Repository.CategoryRepository;
import com.project.Ecommerce.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductResponse productResponse;
    @Autowired
    private FileServiceImpl fileService;
    @Value("${project.image}")
    String path;
    @Override
    public void addProducts(ProductDTO productDTO, Long categoryid) {
        Optional<Product> product =
                productRepository.findByProductNameAndCategory_CategoryId(
                        productDTO.getProductName(),
                        categoryid
                );

        if (product.isPresent()) {
            throw new APIException("Product already exists in this category");
        }
        Category category = categoryRepository.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryid", categoryid));
        Product ogproduct = modelMapper.map(productDTO, Product.class);
        ogproduct.setCategory(category);
        Double splprice = ogproduct.getProductPrice() - (ogproduct.getProductDiscount() * 0.01) * ogproduct.getProductPrice();
        ogproduct.setProductSpecialPrice(splprice);
        productRepository.save(ogproduct);
    }

    @Override
    public ProductResponse getAllProducts() {
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
        productResponse.setContent(productDTOs);

        return productResponse;
    }

    @Override
    public ProductResponse FetchProductById(Long categoryid) {
        Category category = categoryRepository.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryid", categoryid));
        List<Product> products = productRepository.findByCategoryOrderByProductPriceAsc(category);

        List<ProductDTO> productDTOS = products.stream()
                .map(product -> {
                    ProductDTO dto = modelMapper.map(product, ProductDTO.class);
                    return dto;
                })
                .toList();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse FetchProductBykeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> {
                    ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
                    return productDTO;
                })
                .toList();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProductById(ProductDTO productDTO, Long productid) {

        Product product = productRepository.findById(productid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "ProductId", productid)
                );

        Product products = modelMapper.map(productDTO, Product.class);

        product.setProductName(products.getProductName());
        product.setProductDescription(products.getProductDescription());
        product.setProductPrice(products.getProductPrice());
        product.setProductDiscount(products.getProductDiscount());
        product.setImageUrl(products.getImageUrl());
        product.setProductSpecialPrice(products.getProductPrice() - (products.getProductDiscount() * 0.01) + products.getProductPrice());

        Product savedProduct = productRepository.save(product);

        ProductDTO response = modelMapper.map(savedProduct, ProductDTO.class);


        // Set category name manually
        if (savedProduct.getCategory() != null) {
            response.setCategoryName(
                    savedProduct.getCategory().getCategoryName()
            );
        }

        return response;
    }
    @Override
    public void deleteProductbyId(Long productid){
        Product product = productRepository.findById(productid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "ProductId", productid)
                );
        productRepository.delete(product);
    }
    @Override
    public ProductDTO UpdateProductimage(Long productid, MultipartFile image) throws IOException {
        Product product = productRepository.findById(productid)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "ProductId", productid)
                );
        String filename=fileService.UpdateImage(path,image);
        product.setImageUrl(filename);
        Product savedproduct = productRepository.save(product);
        return modelMapper.map(savedproduct, ProductDTO.class);
    }

}

