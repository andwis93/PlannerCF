package com.plannercf.backend.controller;

import com.plannercf.backend.domain.CategoryDto;
import com.plannercf.backend.facade.CategoryFacade;
import com.plannercf.backend.mapper.CategoryMapper;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plannercf/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private CategoryFacade facade;
    private CategoryMapper mapper;

    @Autowired
    public CategoryController(CategoryFacade facade, CategoryMapper mapper) {
        this.facade = facade;
        this.mapper = mapper;
    }

    @PostMapping(value = "{name}")
    public ResponseEntity<CategoryDto> saveCategory(@PathVariable("name") String name) {
        return ResponseEntity.ok(mapper.mapToCategoryDto(facade.saveCategory(name)));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) throws RecordNotExistsException {
        return ResponseEntity.ok(mapper.mapToCategoryDto(facade.getCategory(id)));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(mapper.mapToListCategoryDto(facade.getAllCategories()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,
                                                      @RequestBody CategoryDto categoryDto) throws RecordNotExistsException {
        return ResponseEntity.ok(mapper.mapToCategoryDto(facade.updateCategory(id, categoryDto)));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        facade.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/all")
    public ResponseEntity<Void> deleteAllCategories() {
        facade.deleteAllCategories();
        return ResponseEntity.ok().build();
    }

}
