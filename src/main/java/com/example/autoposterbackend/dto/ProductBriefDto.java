package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Product;
import lombok.Getter;

@Getter
public class ProductBriefDto {
    private Integer id;
    private String name;

    public ProductBriefDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }
}
