package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductImagesResponse {
    private List<ImageDto> images;
}
