package com.plannercf.backend.mapper;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("CategoryMapperTests.class Test Suite")
public class CategoryMapperTests {
    @Autowired
    private CategoryMapper mapper;

    @Test
    void mapToCategoryDtoTest() {
        //Given
        Category category = new Category("Test");

        //When
        CategoryDto categoryDto = mapper.mapToCategoryDto(category);

        //Then
        assertEquals("Test", categoryDto.getName());
    }

    @Test
    void mapToCategoryTest() {
        //Given
        Category category = new Category("Test");
        CategoryDto categoryDto = mapper.mapToCategoryDto(category);
        categoryDto.setName("Changed");

        //When
        Category categoryMapped = mapper.mapToCategory(category, categoryDto);

        //Then
        assertEquals("Changed", categoryMapped.getName());
    }

    @Test
    void mapToListCategoryDtoTest() {
        //Given
        List<Category> categories = List.of(new Category("Test1"), new Category("Test2"));

        //When
        List<CategoryDto> categoriesDto = mapper.mapToListCategoryDto(categories);

        //Then
        assertEquals(2, categoriesDto.size());
    }
}
