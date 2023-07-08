package com.example.autoposterbackend.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductRequest {
    private String name;
    private String title;
    private String description;
    private Integer price;
    private List<String> images;
}
