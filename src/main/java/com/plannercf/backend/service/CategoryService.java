package com.plannercf.backend.service;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.repository.CategoryRepository;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category saveCategory(String name) {
        return repository.save(new Category(name));
    }

    public Category getCategory(Long id) throws RecordNotExistsException {
        return repository.findById(id).orElseThrow(RecordNotExistsException::new);
    }

}
