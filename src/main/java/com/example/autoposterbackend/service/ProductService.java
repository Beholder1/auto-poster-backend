package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.AccountDetailsDto;
import com.example.autoposterbackend.dto.ProductDto;
import com.example.autoposterbackend.dto.response.AccountsDetailsResponse;
import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.entity.Account;
import com.example.autoposterbackend.entity.Product;
import com.example.autoposterbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductsResponse getProducts(Integer userId, String name) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        List<Product> products = productRepository.findByUserIdAndNameLike(userId, name, pageable);
        Integer pages = (int) Math.ceil((double) productRepository.countAllByUserIdAndNameLike(userId, name) / 20);
        return new ProductsResponse(products.stream().map(ProductDto::new).toList(), pages);
    }
}
