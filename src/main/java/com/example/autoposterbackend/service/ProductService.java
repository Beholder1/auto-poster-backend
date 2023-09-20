package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.ProductBriefDto;
import com.example.autoposterbackend.dto.ProductDto;
import com.example.autoposterbackend.dto.request.CreateProductRequest;
import com.example.autoposterbackend.dto.response.ProductsBriefResponse;
import com.example.autoposterbackend.dto.response.ProductsResponse;
import com.example.autoposterbackend.entity.Category;
import com.example.autoposterbackend.entity.Image;
import com.example.autoposterbackend.entity.Product;
import com.example.autoposterbackend.repository.CategoryRepository;
import com.example.autoposterbackend.repository.ImageRepository;
import com.example.autoposterbackend.repository.ProductRepository;
import com.example.autoposterbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    public ProductsResponse getProducts(Integer userId, String name) {
        name = name != null ? name : "";
        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        List<Product> products = productRepository.findAllByUserIdAndNameLike(userId, name, pageable);
        Integer pages = (int) Math.ceil((double) productRepository.countAllByUserIdAndNameLike(userId, name) / 20);
        return new ProductsResponse(products.stream().map(ProductDto::new).toList(), pages);
    }

    public void deleteProduct(Integer userId, Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        imageRepository.deleteByProductId(productId);
        product.getCategories().clear();
        productRepository.deleteById(productId);
    }

    public void createProduct(Integer userId, CreateProductRequest request) throws IOException {
        Product product = new Product();
        if (productRepository.findByUserIdAndName(userId, request.getName()).isPresent()) {
            throw new RuntimeException();
        }
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        if (categories.isEmpty()) {
            throw new RuntimeException();
        }
        product.setUser(userRepository.findById(userId).orElseThrow(RuntimeException::new));
        product.setName(request.getName());
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategories(categories);
        product = productRepository.save(product);
        productRepository.flush();
        List<Image> images = new ArrayList<>();
        for (MultipartFile uploadedImage : request.getImages()) {
            Image image = new Image();
            image.setProduct(product);
            image.setUrl(Objects.requireNonNull(uploadedImage.getBytes()));
            images.add(image);
        }
        imageRepository.saveAll(images);
    }

    public ProductsBriefResponse getProductsBrief(Integer userId) {
        return new ProductsBriefResponse(productRepository.findAllByUserId(userId).stream().map(ProductBriefDto::new).toList());
    }
}
