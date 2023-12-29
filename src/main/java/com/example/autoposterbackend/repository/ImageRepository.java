package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Transactional
    @Modifying
    @Query("delete from Image i where i.productId = ?1")
    int deleteByProductId(Integer productId);

    List<Image> findAllByProductId(Integer productId);
}
