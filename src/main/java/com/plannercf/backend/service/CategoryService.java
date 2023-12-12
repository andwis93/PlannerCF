package com.plannercf.backend.service;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import com.plannercf.backend.mapper.CategoryMapper;
import com.plannercf.backend.repository.CategoryRepository;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public boolean isCategoryExists(Long id) {
        return repository.existsById(id);
    }

    public Category saveCategory(String name) {
        return repository.save(new Category(name));
    }

    public Category getCategory(Long id) throws RecordNotExistsException {
        return repository.findById(id).orElseThrow(RecordNotExistsException::new);
    }

    public List<Category> getAllCategory() {
        return repository.findAll();
    }

    public Category updateCategory(Long id, CategoryDto categoryDto) throws RecordNotExistsException {
        if (isCategoryExists(id)) {
            Category category = getCategory(id);
            return repository.save(mapper.mapToCategory(category, categoryDto));
        } else {
            throw new RecordNotExistsException();
        }
    }
}
