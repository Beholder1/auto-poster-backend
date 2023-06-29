package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteByIdAndUserId(Integer id, Integer userId);

    Optional<Product> findByUserIdAndName(Integer userId, String name);

    @Query("select p from Product p where " +
            "p.userId=:userId " +
            "AND lower(p.name) like lower(concat('%', :name, '%'))")
    List<Product> findAllByUserIdAndNameLike(Integer userId, String name, Pageable pageable);

    @Query("select count(p) from Product p where " +
            "p.userId=:userId " +
            "AND lower(p.name) like lower(concat('%', :name, '%'))")
    Integer countAllByUserIdAndNameLike(Integer userId, String name);

    List<Product> findAllByUserId(Integer userId);
}
