package com.plannercf.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
