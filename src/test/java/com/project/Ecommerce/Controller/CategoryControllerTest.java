package com.project.Ecommerce.Controller;

import com.project.Ecommerce.Model.Category;
import com.project.Ecommerce.Service.CategoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryInterface categoryInterface;

    @InjectMocks
    private CategoryController categoryController;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1L,"wood");
        category.setCategoryId(1L);
        category.setCategoryName("Electronics");
    }

    // --------------------------------------------------
    // GET ALL CATEGORIES
    // --------------------------------------------------

    @Test
    void getallCategory_shouldReturnCategories() {

        List<Category> categories = List.of(category);

        when(categoryInterface.getallCategories())
                .thenReturn(categories);

        ResponseEntity<List<Category>> response =
                categoryController.getallCategory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());

        verify(categoryInterface, times(1))
                .getallCategories();
    }

    @Test
    void getallCategory_shouldReturnEmptyList() {

        when(categoryInterface.getallCategories())
                .thenReturn(List.of());

        ResponseEntity<List<Category>> response =
                categoryController.getallCategory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(categoryInterface, times(1))
                .getallCategories();
    }


    // --------------------------------------------------
    // POST CATEGORY
    // --------------------------------------------------

    @Test
    void addCategories_shouldCreateCategory() {

        doNothing()
                .when(categoryInterface)
                .createCategory(category);

        ResponseEntity<String> response =
                categoryController.addCategories(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(
                "Category Added Sucessfully",
                response.getBody()
        );

        verify(categoryInterface, times(1))
                .createCategory(category);
    }


    // --------------------------------------------------
    // DELETE CATEGORY
    // --------------------------------------------------

    @Test
    void deleteCategories_shouldDeleteCategory() {

        when(categoryInterface.DeletecategoryId(1L))
                .thenReturn("Category deleted successfully");

        ResponseEntity<String> response =
                categoryController.deleteCatogories(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(
                "Category deleted successfully",
                response.getBody()
        );

        verify(categoryInterface, times(1))
                .DeletecategoryId(1L);
    }

    @Test
    void deleteCategories_shouldReturnExceptionStatus() {

        ResponseStatusException exception =
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                );

        when(categoryInterface.DeletecategoryId(1L))
                .thenThrow(exception);

        ResponseEntity<String> response =
                categoryController.deleteCatogories(1L);

        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );

        assertEquals(
                "Category not found",
                response.getBody()
        );

        verify(categoryInterface, times(1))
                .DeletecategoryId(1L);
    }


    // --------------------------------------------------
    // UPDATE CATEGORY
    // --------------------------------------------------

    @Test
    void updateCategories_shouldUpdateCategory() {

        when(categoryInterface.UpdateCategories(1L, category))
                .thenReturn("Category updated successfully");

        ResponseEntity<String> response =
                categoryController.UpdateCategories(1L, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(
                "Category updated successfully",
                response.getBody()
        );

        verify(categoryInterface, times(1))
                .UpdateCategories(1L, category);
    }

    @Test
    void updateCategories_shouldReturnExceptionStatus() {

        ResponseStatusException exception =
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                );

        when(categoryInterface.UpdateCategories(1L, category))
                .thenThrow(exception);

        ResponseEntity<String> response =
                categoryController.UpdateCategories(1L, category);

        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );

        assertEquals(
                "Category not found",
                response.getBody()
        );

        verify(categoryInterface, times(1))
                .UpdateCategories(1L, category);
    }
}
