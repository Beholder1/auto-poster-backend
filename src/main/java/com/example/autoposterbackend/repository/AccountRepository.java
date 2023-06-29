package com.example.autoposterbackend.repository;

import com.example.autoposterbackend.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    void deleteByIdAndUserId(Integer id, Integer userId);

    List<Account> findAllByUserId(Integer userId);

    Optional<Account> findByUserIdAndName(Integer userId, String name);

    @Query("select a from Account a where " +
            "a.userId=:userId " +
            "AND lower(a.name) like lower(concat('%', :name, '%'))")
    List<Account> findAllByUserIdAndNameLike(Integer userId, String name, Pageable pageable);

    @Query("select count(a) from Account a where " +
            "a.userId=:userId " +
            "AND lower(a.name) like lower(concat('%', :name, '%'))")
    Integer countAllByUserIdAndNameLike(Integer userId, String name);
}
