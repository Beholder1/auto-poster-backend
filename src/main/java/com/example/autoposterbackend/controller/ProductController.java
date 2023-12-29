package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateProductRequest;
import com.example.autoposterbackend.dto.response.ProductImagesResponse;
import com.example.autoposterbackend.dto.response.ProductsBriefResponse;
import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{userId}")
    public ProductsResponse getProducts(@PathVariable Integer userId, @RequestParam(required = false) String name) {
        return productService.getProducts(userId, name);
    }

    @GetMapping("/{userId}/brief")
    public ProductsBriefResponse getProductsBrief(@PathVariable Integer userId) {
        return productService.getProductsBrief(userId);
    }

    @DeleteMapping("/{userId}/{productId}")
    public void deleteProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
        productService.deleteProduct(userId, productId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@PathVariable Integer userId, @ModelAttribute CreateProductRequest request) throws IOException {
        productService.createProduct(userId, request);
    }

    @GetMapping("/{productId}/images")
    public ProductImagesResponse getProductImages(@PathVariable Integer productId) {
        return productService.getProductImages(productId);
    }
}
