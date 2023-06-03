package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.AccountDto;
import com.example.autoposterbackend.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LocationsResponse {
    private List<LocationDto> locations;
    private Integer pages;
}
