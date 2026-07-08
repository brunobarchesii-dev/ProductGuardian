package com.brunobarchesi.ProductGuardian.service;

import com.brunobarchesi.ProductGuardian.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



}
