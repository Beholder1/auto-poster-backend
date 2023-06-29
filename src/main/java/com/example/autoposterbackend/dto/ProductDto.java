package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Category;
import com.example.autoposterbackend.entity.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductDto {
    private Integer id;
    private String name;
    private String title;
    private String description;
    private Integer price;
    private List<String> categories;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categories = product.getCategories().stream().map(Category::getName).toList();
    }
}
