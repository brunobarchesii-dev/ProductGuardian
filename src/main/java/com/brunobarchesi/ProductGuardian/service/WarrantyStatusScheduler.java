package com.brunobarchesi.ProductGuardian.service;

import com.brunobarchesi.ProductGuardian.dto.enums.WarrantyStatusEnum;
import com.brunobarchesi.ProductGuardian.entity.ProductEntity;
import com.brunobarchesi.ProductGuardian.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WarrantyStatusScheduler {

     private final ProductRepository productRepository;
     private final ProductService productService;

     //metodo disparado diariamente que atualiza o status da garantia
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    @Transactional
    public void updateWarrantyStatus() {
         List<ProductEntity> products = productRepository.findAll();
         for (ProductEntity product : products){
             //compara se o status da garantia mudou:
             WarrantyStatusEnum newStatus = productService.getWarrantyStatus(product.getWarrantyEndDate());
             if (!product.getWarrantyStatus().equals(newStatus)) {
                 product.setWarrantyStatus(newStatus);
             }
             //Como o metodo esta anotado com @Transactional nao preciso dar um save. Ele
             //faz automaticamente.
         }

     }
}
