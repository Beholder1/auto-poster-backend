package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateLocationRequest;
import com.example.autoposterbackend.dto.request.CreateProductRequest;
import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{userId}/{productId}")
    public void deleteProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
        productService.deleteProduct(userId, productId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@PathVariable Integer userId, CreateProductRequest request) {
        productService.createProduct(userId, request);
    }
}
