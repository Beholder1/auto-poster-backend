package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.LocationDto;
import com.example.autoposterbackend.dto.request.CreateAccountRequest;
import com.example.autoposterbackend.dto.request.CreateLocationRequest;
import com.example.autoposterbackend.dto.response.LocationsResponse;
import com.example.autoposterbackend.entity.Account;
import com.example.autoposterbackend.entity.Location;
import com.example.autoposterbackend.repository.LocationRepository;
import com.example.autoposterbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public LocationsResponse getLocations(Integer userId, String name) {
        name = name != null ? name : "";
        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        List<Location> locations = locationRepository.findByUserIdAndNameLike(userId, name, pageable);
        Integer pages = (int) Math.ceil((double) locationRepository.countAllByUserIdAndNameLike(userId, name) / 20);
        return new LocationsResponse(locations.stream().map(LocationDto::new).toList(), pages);
    }

    public void deleteLocation(Integer userId, Integer locationId) {
        locationRepository.deleteByIdAndUserId(locationId, userId);
    }

    public void createLocation(Integer userId, CreateLocationRequest request) {
        Location location = new Location();
        if (locationRepository.findByUserIdAndName(userId, request.getName()).isPresent()) {
            throw new RuntimeException();
        }
        location.setUser(userRepository.findById(userId).orElseThrow(RuntimeException::new));
        location.setName(request.getName());
        locationRepository.save(location);
    }
}
