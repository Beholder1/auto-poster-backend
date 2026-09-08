package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Category;
import com.example.autoposterbackend.entity.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class ScriptProductDto {
    private Integer id;
    private String name;
    private String title;
    private String description;
    private Integer price;
    private List<Integer> categoryIds;
    private List<Integer> imageIds;

    public ScriptProductDto(Product product, List<Integer> imageIds) {
        this.id = product.getId();
        this.name = product.getName();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categoryIds = product.getCategories().stream().map(Category::getId).toList();
        this.imageIds = imageIds;
    }
}
