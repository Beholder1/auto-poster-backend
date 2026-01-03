package com.example.autoposterbackend.controller;

import com.example.autoposterbackend.dto.request.CreateProductRequest;
import com.example.autoposterbackend.dto.response.ProductImagesResponse;
import com.example.autoposterbackend.dto.response.ProductsBriefResponse;
import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.entity.User;
import com.example.autoposterbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ProductsResponse getProducts(@AuthenticationPrincipal User user, @RequestParam(required = false) String name) {
        return productService.getProducts(user.getId(), name);
    }

    @GetMapping("/brief")
    public ProductsBriefResponse getProductsBrief(@AuthenticationPrincipal User user) {
        return productService.getProductsBrief(user.getId());
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@AuthenticationPrincipal User user, @PathVariable Integer productId) {
        productService.deleteProduct(user.getId(), productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@AuthenticationPrincipal User user, @ModelAttribute CreateProductRequest request) throws IOException {
        productService.createProduct(user.getId(), request);
    }

    @GetMapping("/{productId}/images")
    public ProductImagesResponse getProductImages(@PathVariable Integer productId) {
        return productService.getProductImages(productId);
    }
}
