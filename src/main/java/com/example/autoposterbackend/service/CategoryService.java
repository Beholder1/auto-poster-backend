package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.CategoryDto;
import com.example.autoposterbackend.dto.response.CategoriesResponse;
import com.example.autoposterbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoriesResponse getCategories() {
        return new CategoriesResponse(categoryRepository.findAll().stream().map(CategoryDto::new).sorted(Comparator.comparing(CategoryDto::getName)).toList());
    }
}
