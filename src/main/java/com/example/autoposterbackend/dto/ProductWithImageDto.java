package com.example.autoposterbackend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductWithImageDto {
    private Integer productId;
    private List<Integer> imageIds;
}
