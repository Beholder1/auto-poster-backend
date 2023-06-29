package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CategoriesResponse {
    private List<CategoryDto> categories;
}
