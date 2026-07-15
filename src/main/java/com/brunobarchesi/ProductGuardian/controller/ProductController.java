package com.brunobarchesi.ProductGuardian.controller;

import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductRequestDTO;
import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductResponseDTO;
import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductUpdateDTO;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import com.brunobarchesi.ProductGuardian.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@AuthenticationPrincipal UserEntity currentUser,
            @Valid @RequestBody ProductRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto, currentUser));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findMyProducts(
            @AuthenticationPrincipal UserEntity currentUser
    ) {
        return ResponseEntity.ok(productService.findMyProducts(currentUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findMyProductById(@PathVariable UUID id,
            @AuthenticationPrincipal UserEntity currentUser
    ) {
        return ResponseEntity.ok(productService.findMyProductById(id, currentUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserEntity currentUser,
            @Valid @RequestBody ProductUpdateDTO dto
    ) {
        return ResponseEntity.ok(productService.update(id, dto, currentUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @AuthenticationPrincipal UserEntity currentUser
    ) {
        productService.delete(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}

