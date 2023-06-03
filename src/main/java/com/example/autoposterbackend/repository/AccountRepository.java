package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUserId(Integer userId);
    List<Account> findByUserIdAndNameLike(Integer userId, String name, Pageable pageable);
    Integer countAllByUserIdAndNameLike(Integer userId, String name);
}
