package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateLocationRequest;
import com.example.autoposterbackend.dto.response.LocationsResponse;
import com.example.autoposterbackend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/{userId}")
    public LocationsResponse getLocations(@PathVariable Integer userId, String name) {
        return locationService.getLocations(userId, name);
    }

    @DeleteMapping("/{userId}/{locationId}")
    public void deleteLocation(@PathVariable Integer userId, @PathVariable Integer locationId) {
        locationService.deleteLocation(userId, locationId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLocation(@PathVariable Integer userId, @RequestBody CreateLocationRequest request) {
        locationService.createLocation(userId, request);
    }
}
