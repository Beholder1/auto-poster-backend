package com.example.autoposterbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
