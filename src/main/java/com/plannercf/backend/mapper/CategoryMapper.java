package com.plannercf.backend.mapper;

import com.plannercf.backend.domain.Category;
import com.plannercf.backend.domain.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryMapper {

    public CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public List<CategoryDto> mapToListCategoryDto(List<Category> categories) {
        return categories.stream().map(this::mapToCategoryDto).collect(Collectors.toList());
    }
}
