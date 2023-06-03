package com.example.autoposterbackend.service;

import com.example.autoposterbackend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
}
