package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{userId}")
    public ProductsResponse getProducts(@PathVariable Integer userId, @RequestParam(required = false) String name) {
        return productService.getProducts(userId, name);
    }
}
