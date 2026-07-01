package com.brunobarchesi.ProductGuardian.dto.productdtos;

import com.brunobarchesi.ProductGuardian.dto.enums.ProductCategoryEnum;
import com.brunobarchesi.ProductGuardian.dto.enums.WarrantyStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String description,
        ProductCategoryEnum category,
        String brand,
        String model,
        String storeName,
        LocalDate purchaseDate,
        BigDecimal purchasePrice,
        Integer warrantyMonths,
        LocalDate warrantyEndDate,
        WarrantyStatusEnum warrantyStatus,
        String invoiceNumber,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
