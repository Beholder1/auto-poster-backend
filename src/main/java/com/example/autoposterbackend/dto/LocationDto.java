package com.example.autoposterbackend.dto;

import com.example.autoposterbackend.entity.Location;
import lombok.Getter;

@Getter
public class LocationDto {
    private Integer id;
    private String name;

    public LocationDto(Location location) {
        this.id = location.getId();
        this.name = location.getName();
    }
}
