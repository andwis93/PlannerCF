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
import java.util.List;
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
    List<Category> categories = List.of(category, new Category(2L, "Test2"));
    List<CategoryDto> categoriesDto = List.of(categoryDto, new CategoryDto(2L, "TestDto2"));


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
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("TestDto")));
    }

    @Test
    void shouldGetCategory() throws Exception {
        //Given
        when(facade.getCategory(1L)).thenReturn(category);
        when(mapper.mapToCategoryDto(category)).thenReturn(categoryDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/plannercf/category/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("TestDto")));
    }

    @Test
    void shouldGetAllCategories() throws Exception {
        //Given
        when(facade.getAllCategories()).thenReturn(categories);
        when(mapper.mapToListCategoryDto(categories)).thenReturn(categoriesDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/plannercf/category/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("TestDto2")));
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        //Given
        Category category1 = new Category(1L,"TestChanged");
        CategoryDto categoryDto1 = new CategoryDto(1L,"TestDtoChanged");
        when(facade.updateCategory(1L,categoryDto1)).thenReturn(category1);
        when(mapper.mapToCategoryDto(category1)).thenReturn(categoryDto1);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(categoryDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/plannercf/category/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("TestDtoChanged")));
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        //Given
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/plannercf/category/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldDeleteAllCategories() throws Exception {
        //Given
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/plannercf/category/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
