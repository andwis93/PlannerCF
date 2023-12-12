package com.plannercf.backend.facade;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.service.CategoryService;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryFacade {
    private final CategoryService service;

    @Autowired
    public CategoryFacade(CategoryService service) {
        this.service = service;
    }

    public Category saveCategory(String name) {
        return service.saveCategory(name);
    }

    public Category getCategory(Long id) throws RecordNotExistsException {
        return service.getCategory(id);
    }
}
