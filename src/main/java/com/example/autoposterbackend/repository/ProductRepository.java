package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByUserIdAndNameLike(Integer userId, String name, Pageable pageable);

    Object countAllByUserIdAndNameLike(Integer userId, String name);
}
