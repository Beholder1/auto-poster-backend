package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Integer> {
    PasswordReset findByToken(String token);

    Optional<PasswordReset> findByUser_Id(Integer userId);
}
