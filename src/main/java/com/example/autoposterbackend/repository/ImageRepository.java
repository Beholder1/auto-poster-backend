package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}