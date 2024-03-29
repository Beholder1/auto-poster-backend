package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.response.CategoriesResponse;
import com.example.autoposterbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public CategoriesResponse getCategories() {
        return categoryService.getCategories();
    }
}
