package com.plannercf.backend.controller;

import com.google.gson.Gson;
import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import com.plannercf.backend.facade.CategoryFacade;
import com.plannercf.backend.mapper.CategoryMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CategoryController.class)
@DisplayName("CategoryControllerTests.class Tests Suite")
public class CategoryControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryFacade facade;
    @MockBean
    private CategoryMapper mapper;

    Category category = new Category(1L,"Test");
    CategoryDto categoryDto = new CategoryDto(1L,"TestDto");

    @Test
    void shouldSaveCategory() throws Exception {
        //Given
        when(facade.saveCategory("Test")).thenReturn(category);
        when(mapper.mapToCategoryDto(category)).thenReturn(categoryDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(categoryDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/plannercf/category/Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .contentType(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("TestDto")));
    }

}
