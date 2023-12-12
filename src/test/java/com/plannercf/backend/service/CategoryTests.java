package com.plannercf.backend.service;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import com.plannercf.backend.service.exception.RecordAlreadyExistsException;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import com.plannercf.backend.service.exception.TestNotCleaned;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("CategoryTests.class Test Suite")
public class CategoryTests {
    @Autowired
    private CategoryService service;

    @Test
    void isCategoryExistsByIdTest() throws TestNotCleaned, RecordAlreadyExistsException {
        //Given & When
        Long id = service.saveCategory("Test").getId();

        //Then
        assertTrue(service.isCategoryExists(id));

        //Clean
        try {
            service.deleteAllCategories();
        } catch (Exception err) {
            throw new TestNotCleaned("isCategoryExistsByIdTest NOT cleaned");
        }
    }

    @Test
    void saveCategoryTest() throws RecordNotExistsException, TestNotCleaned, RecordAlreadyExistsException {
        //Given & When
        Long id = service.saveCategory("Test").getId();
        Category categoryRetrieved = service.getCategory(id);

        //Then
        assertEquals("Test", categoryRetrieved.getName());

        //Clean
        try {
            service.deleteAllCategories();
        } catch (Exception err) {
            throw new TestNotCleaned("saveCategoryTest NOT cleaned");
        }
    }
    @Test
    void getAllCategoriesTest() throws TestNotCleaned {
        //Given
        service.saveCategory("Test");

        //When
        List<Category> categories = service.getAllCategories();

        //Then
        assertEquals(1, categories.size());

        //Clean
        try {
            service.deleteAllCategories();
        } catch (Exception err) {
            throw new TestNotCleaned("getAllCategoriesTest NOT cleaned");
        }
    }

    @Test
    void updateCategoryTest() throws RecordNotExistsException, TestNotCleaned {
        //Given
        Long id = service.saveCategory("Test").getId();

        //When
        Category categoryChanged = service.updateCategory(id, new CategoryDto("Changed"));

        //Then
        assertEquals("Changed", categoryChanged.getName());

        //Clean
        try {
            service.deleteAllCategories();
        } catch (Exception err) {
            throw new TestNotCleaned("updateCategoryTest NOT cleaned");
        }
    }

    @Test
    void deleteCategoryTest() throws TestNotCleaned {
        //Given
        Long id = service.saveCategory("Test").getId();
        boolean isSaved =  service.isCategoryExists(id);

        //When
        service.deleteCategory(id);

        //Then
        assertTrue(isSaved);
        assertFalse(service.isCategoryExists(id));

        //Clean
        try {
            service.deleteAllCategories();
        } catch (Exception err) {
            throw new TestNotCleaned("deleteCategoryTest NOT cleaned");
        }
    }
}
