package com.plannercf.backend.mapper;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
