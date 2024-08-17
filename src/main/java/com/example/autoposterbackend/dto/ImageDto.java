package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Image;
import lombok.Data;

import java.util.Base64;

@Data
public class ImageDto {
    private Integer id;
    private String url;

    public ImageDto(Image image) {
        this.id = image.getId();
        this.url = Base64.getEncoder().encodeToString(image.getUrl());
    }
}
