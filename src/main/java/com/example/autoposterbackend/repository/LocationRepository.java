package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    void deleteByIdAndUserId(Integer id, Integer userId);

    @Query("select l from Location l where " +
            "l.userId=:userId " +
            "AND lower(l.name) like lower(concat('%', :name, '%'))")
    List<Location> findAllByUserIdAndNameLike(Integer userId, String name, Pageable pageable);

    @Query("select count(l) from Location l where " +
            "l.userId=:userId " +
            "AND lower(l.name) like lower(concat('%', :name, '%'))")
    Integer countAllByUserIdAndNameLike(Integer userId, String name);

    Optional<Location> findByUserIdAndName(Integer userId, String name);

    List<Location> findAllByUserId(Integer userId);
}
