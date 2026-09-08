package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScriptLocationsResponse {
    private List<LocationDto> locations;
}
