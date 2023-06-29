package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.ProductBriefDto;
import com.example.autoposterbackend.dto.ProductDto;
import com.example.autoposterbackend.dto.request.CreateProductRequest;
import com.example.autoposterbackend.dto.response.ProductsBriefResponse;
import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.entity.Product;
import com.example.autoposterbackend.repository.ProductRepository;
import com.example.autoposterbackend.repository.UserRepository;
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
    private final UserRepository userRepository;

    public ProductsResponse getProducts(Integer userId, String name) {
        name = name != null ? name : "";
        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        List<Product> products = productRepository.findAllByUserIdAndNameLike(userId, name, pageable);
        Integer pages = (int) Math.ceil((double) productRepository.countAllByUserIdAndNameLike(userId, name) / 20);
        return new ProductsResponse(products.stream().map(ProductDto::new).toList(), pages);
    }

    public void deleteProduct(Integer userId, Integer productId) {
        productRepository.deleteById(productId);
    }

    public void createProduct(Integer userId, CreateProductRequest request) {
        Product product = new Product();
        if (productRepository.findByUserIdAndName(userId, request.getName()).isPresent()) {
            throw new RuntimeException();
        }
        product.setUser(userRepository.findById(userId).orElseThrow(RuntimeException::new));
        product.setName(request.getName());
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        productRepository.save(product);
    }

    public ProductsBriefResponse getProductsBrief(Integer userId) {
        return new ProductsBriefResponse(productRepository.findAllByUserId(userId).stream().map(ProductBriefDto::new).toList());
    }
}
