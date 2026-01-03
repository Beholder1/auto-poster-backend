package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateLocationRequest;
import com.example.autoposterbackend.dto.request.EditLocationRequest;
import com.example.autoposterbackend.dto.response.LocationsResponse;
import com.example.autoposterbackend.entity.User;
import com.example.autoposterbackend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public LocationsResponse getLocations(@AuthenticationPrincipal User user, String name) {
        return locationService.getLocations(user.getId(), name);
    }

    @DeleteMapping("/{locationId}")
    public void deleteLocation(@AuthenticationPrincipal User user, @PathVariable Integer locationId) {
        locationService.deleteLocation(user.getId(), locationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLocation(@AuthenticationPrincipal User user, @RequestBody CreateLocationRequest request) {
        locationService.createLocation(user.getId(), request);
    }

    @PutMapping
    public void editLocation(@AuthenticationPrincipal User user, @RequestBody EditLocationRequest request) {
        locationService.editLocation(user.getId(), request);
    }
}
