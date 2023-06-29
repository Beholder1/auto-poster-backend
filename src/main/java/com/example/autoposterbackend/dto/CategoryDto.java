package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Category;
import lombok.Getter;

@Getter
public class CategoryDto {
    private Integer id;
    private String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
