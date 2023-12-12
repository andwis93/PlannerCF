package com.plannercf.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryDto extends BaseEntityDto{
    private String name;

    public CategoryDto(Long id, String name) {
        super(id);
        this.name = name;
    }
    public CategoryDto(String name) {
        this.name = name;
    }
}
