package com.example.autoposterbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
