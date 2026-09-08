package com.example.autoposterbackend.service;

import com.example.autoposterbackend.dto.AccountDetailsDto;
import com.example.autoposterbackend.dto.LocationDto;
import com.example.autoposterbackend.dto.ScriptProductDto;
import com.example.autoposterbackend.dto.response.ScriptAccountsResponse;
import com.example.autoposterbackend.dto.response.ScriptLocationsResponse;
import com.example.autoposterbackend.dto.response.ScriptProductsResponse;
import com.example.autoposterbackend.entity.Account;
import com.example.autoposterbackend.entity.Product;
import com.example.autoposterbackend.repository.AccountRepository;
import com.example.autoposterbackend.repository.ImageRepository;
import com.example.autoposterbackend.repository.LocationRepository;
import com.example.autoposterbackend.repository.ProductRepository;
import com.example.autoposterbackend.util.AccountEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dane dla skryptow uruchamianych po stronie klienta (wstawianie ogloszen,
 * odswiezanie, aktywnosc). Sam przebieg skryptow nie jest wykonywany na serwerze.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ScriptDataService {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final ImageRepository imageRepository;
    private final AccountEncoder accountEncoder;

    public ScriptAccountsResponse getAccounts(Integer userId, List<Integer> accountIds) {
        List<Account> accounts = accountIds == null || accountIds.isEmpty()
                ? accountRepository.findAllByUserId(userId)
                : accountRepository.findAllByUserIdAndIdIn(userId, accountIds);
        return new ScriptAccountsResponse(accounts.stream().map(account -> {
            AccountDetailsDto accountDetailsDto = new AccountDetailsDto(account);
            try {
                accountDetailsDto.setPassword(accountEncoder.decrypt(account.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return accountDetailsDto;
        }).toList());
    }

    public ScriptProductsResponse getProducts(Integer userId, List<Integer> productIds) {
        List<Product> products = productIds == null || productIds.isEmpty()
                ? productRepository.findAllByUserId(userId)
                : productRepository.findAllByUserIdAndIdIn(userId, productIds);
        return new ScriptProductsResponse(products.stream()
                .map(product -> new ScriptProductDto(product, imageRepository.findAllIdsByProductId(product.getId())))
                .toList());
    }

    public ScriptLocationsResponse getLocations(Integer userId) {
        return new ScriptLocationsResponse(
                locationRepository.findAllByUserId(userId).stream().map(LocationDto::new).toList());
    }
}
