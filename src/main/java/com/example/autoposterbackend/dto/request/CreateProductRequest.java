package com.example.autoposterbackend.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private String title;
    private String description;
    private Integer price;
    private List<Integer> categoryIds;
    private List<MultipartFile> images;
}
