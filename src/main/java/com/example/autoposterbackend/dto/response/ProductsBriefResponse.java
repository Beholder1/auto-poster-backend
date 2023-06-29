package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.ProductBriefDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductsBriefResponse {
    private List<ProductBriefDto> products;
}
