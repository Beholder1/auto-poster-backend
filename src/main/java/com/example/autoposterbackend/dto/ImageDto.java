package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Image;
import lombok.Data;

@Data
public class ImageDto {
    private Integer id;
    private byte[] url;

    public ImageDto(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
    }
}
