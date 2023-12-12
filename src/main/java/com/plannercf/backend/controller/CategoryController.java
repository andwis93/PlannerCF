package com.plannercf.backend.controller;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import com.plannercf.backend.facade.PCFFacade;
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
    private PCFFacade facade;

    @Autowired
    public CategoryController(PCFFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "{name}")
    public ResponseEntity<CategoryDto> saveCategory(@PathVariable("name") String name) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/all")
    public ResponseEntity<Void> deleteAllCategories() {
        return ResponseEntity.ok().build();
    }

}
