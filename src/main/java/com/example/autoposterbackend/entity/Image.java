package com.example.autoposterbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private byte[] url;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
