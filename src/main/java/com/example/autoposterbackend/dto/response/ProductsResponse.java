package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductsResponse {
    private List<ProductDto> accounts;
    private Integer pages;
}
